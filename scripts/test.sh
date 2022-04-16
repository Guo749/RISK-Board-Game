#!/bin/bash

./gradlew test-shared || exit 1
./gradlew cloverGenerateReport || exit 1
./gradlew cloverAggregateReports || exit 1
scripts/coverage_summary.sh
ls -l /
ls -l /coverage-out/
cp -r shared/build/reports/clover/html/* /coverage-out/ || exit 1
#cp -r build/reports/clover/html/* /coverage-out/ || exit 1
