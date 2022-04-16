package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Helper Function, used to handle common situation for different pages
 */
public class Helper {

    /**
     * Given the player information from the server, get all regions that belongs to me.
     *
     * @param info the player info that server sends to client
     * @return all my regions in the list
     */
    public static List<Region> getMyRegions(PlayerInfo info){
        List<Region> res = new ArrayList<>();
        for(Region region : info.getMap().getRegions()){
            if(region.getOwnerID() == info.getMap().getPlayerMapping(info.getPlayerId()))
                res.add(region);
        }

        return res;
    }

    public static List<Region> getAllRegions(PlayerInfo info){
        List<Region> res = new ArrayList<>();
        for(Region region : info.getMap().getRegions()){
            res.add(region);
        }

        return res;
    }

    /**
     * Given the player information from the server, get all regions that does not belongs to me
     *
     * @param info the player info that server sends to client
     * @return all enemies' region in the list
     */
    public static List<Region> getEnemyRegion(PlayerInfo info){
        List<Region> res = new ArrayList<>();
        for(Region region : info.getMap().getRegions()){
            if(region.getOwnerID() != info.getMap().getPlayerMapping(info.getPlayerId()))
                res.add(region);
        }

        return res;
    }

    /**
     * Given info, get all units number from different regions
     *
     * @param info the player info
     * @return the number for my units
     */
    public static int getUnitsNum(PlayerInfo info){
        int total = 0;
        for(Region region : info.getMap().getRegions()){
            if(region.getOwnerID() == info.getMap().getPlayerMapping(info.getPlayerId()))
                total += region.getArmy().getUnits().size();
        }

        return total;
    }

    /**
     * This is used to initialize the map image for
     *
     * @param mapNum corresponding to the map that I load
     * @return
     */
    public static Image initializeMap(int mapNum){
        if(mapNum == 2)
            return new Image(Helper.class.getResource("/fxml/map2.jpg").toString());
        else if(mapNum == 3)
            return new Image(Helper.class.getResource("/fxml/map3.png").toString());
        else if(mapNum == 4)
            return new Image(Helper.class.getResource("/fxml/map4.png").toString());
        else
            return new Image(Helper.class.getResource("/fxml/map5.png").toString());
    }

    /**
     * to initialize the map based on the class of info
     * we will pass info with Abstract Map and Polymorphism to handle different maps
     *
     * @param info player info
     * @return the map number, like map with two persons, we will return 2
     */
    public static int initializeMapNum(PlayerInfo info) {
        if(info.getMap().getClass() == MapForTwo.class){
            return 2;
        }else if(info.getMap().getClass() == MapForThree.class){
            return 3;
        }else if(info.getMap().getClass() == MapForFour.class){
            return 4;
        }else if(info.getMap().getClass() == MapForFive.class){
            return 5;
        }

        throw new IllegalArgumentException("Wrong Argu for Map Number");
    }


    /**
     * This is used to get the scene from FXML files
     *
     * @param path the path to get the file
     * @return the scene to show
     * @throws IOException
     */
    public static Scene getScene(String path) throws IOException {
        URL xmlRes = Helper.class.getResource(path);
        assert (xmlRes != null);

        AnchorPane ap = FXMLLoader.load(xmlRes);

        Scene scene =  new Scene(ap, 1280, 800);
        scene.getStylesheets().add(Helper.class.getResource("/fxml/style.css").toString());
        return scene;
    }

    public static Scene getScene(String path, int width, int height) throws IOException {
        URL xmlRes = Helper.class.getResource(path);
        assert (xmlRes != null);

        AnchorPane ap = FXMLLoader.load(xmlRes);
        Scene scene =  new Scene(ap, width, height);
        String cssRes = Helper.class.getResource("/fxml/style.css").toString();
        assert(cssRes != null);
        scene.getStylesheets().add(cssRes);
        return scene;
    }

    public static void sendAction(Action action) throws Exception {
        if(!action.getString().equals(""))
            Player.actionStringList.add(0, action.getString());
        try{
            App.player.outputStream.writeObject(action);
            App.player.outputStream.reset();
            System.out.println("In Run");
            App.player.run();

        }catch (Exception e){
//            Scene scene = LoginController.show();
//            if (App.ps != null)
//                App.ps.setScene(scene);
//            try{
//                App.player.playerSocket = new Socket(App.player.ipAddr, App.player.port);
//                App.player.outputStream = new ObjectOutputStream(App.player.playerSocket.getOutputStream());
//                App.player.inputStream = new ObjectInputStream(App.player.playerSocket.getInputStream());
//                scene = LoginController.show();
//                if (App.ps != null)
//                    App.ps.setScene(scene);
//            }catch(IOException e1){
//                System.out.println("Connection failed");
//            }
        }
    }

    public static int parseInt(String num){
        int res = 0;
        if(num == null)
            return Integer.MAX_VALUE;
        for(char ch : num.toCharArray()){
            if(ch < '0' || ch > '9')
                return Integer.MAX_VALUE;

            res *= 10;
            res += ch - '0';
        }

        return num.length() == 0 ? Integer.MAX_VALUE : res;
    }

    public static void sendNullAction(Socket clientSocket) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

        ois.readObject();
        oos.writeObject(null);
        oos.reset();
    }

    public static Action getChooseNumAction(String item){
        Action action = null;
        if(item.equals("Create Map2")){
            action = new ChoosePlayerNumAction(2);
        }else if(item.equals("Create Map3")){
            action = new ChoosePlayerNumAction(3);
        }else if(item.equals("Create Map4")){
            action = new ChoosePlayerNumAction(4);
        }else if(item.equals("Create Map5")){
            action = new ChoosePlayerNumAction(5);
        }

        return action;
    }

    public static void initializeBtn(PlayerInfo info, AbstractMap map, Button btn1, Button btn2, Button btn3, Button btn4, Button btn5, Button btn6) {
        if(info.getMap().getClass() != MapForTwo.class) {
            btn1.setVisible(false);
            btn2.setVisible(false);
            btn3.setVisible(false);
            btn4.setVisible(false);
            btn5.setVisible(false);
            btn6.setVisible(false);
            return;
        }
        MapForTwo map2 = (MapForTwo) map;

        Map<String, Region> regionFinder = map2.getRegionFinder();
        Region Beijing = regionFinder.get("Beijing");
        Region Chengdu = regionFinder.get("Chengdu");
        Region Suzhou = regionFinder.get("Suzhou");
        Region Shanghai = regionFinder.get("Shanghai");
        Region Guangzhou = regionFinder.get("Guangzhou");
        Region Kunming = regionFinder.get("Kunming");

        int ID = map.getPlayerMapping(info.getPlayerId());

        btn1.setText(  (Beijing.getOwnerID()   == ID ?  "Beijing"   : "Beijing " + Beijing.getOwnerID()));
        btn2.setText(  (Chengdu.getOwnerID()   == ID ?  "Chengdu"   : "Chengdu " + Chengdu.getOwnerID()));
        btn3.setText(   (Suzhou.getOwnerID()    == ID ? "Suzhou"    : "Suzhou " + Suzhou.getOwnerID()));
        btn6.setText(  (Kunming.getOwnerID()   == ID ?  "Kunming"   : "Kunming " + Kunming.getOwnerID()));
        btn5.setText((Guangzhou.getOwnerID() == ID ?    "Guangzhou" : "Guangzhou " + Guangzhou.getOwnerID()));
        btn4.setText( (Shanghai.getOwnerID()  == ID ?   "Shanghai"  : "Shanghai " + Shanghai.getOwnerID()));

    }

    public static void hitSwitch(PlayerInfo info) throws Exception {
        Action login = new LoginAction(info.getAccount(), info.getPassword());
        Helper.sendAction(login);
    }

    public static void showContent(MouseEvent ae, PlayerInfo info, TextArea textArea){
        TextView view = new TextView(info.getMap());

        if(info.getMap().getClass() != MapForTwo.class){
            String getDisplay = view.displayRegionInString(info.getPlayerId(), true);
            textArea.setVisible(true);
            textArea.setText(getDisplay);
            return;
        }
        Ellipse ellipse = (Ellipse) ae.getSource();
        String text = ellipse.getId();


        String getDisplay = view.printRegionInfo(text + " ", info);

        textArea.setVisible(true);
        textArea.setText(getDisplay);

    }

    public static void hideContent(TextArea textArea){
//        textArea.clear();
//        textArea.setVisible(false);
    }
}
