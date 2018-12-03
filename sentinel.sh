#! /bin/bash

nohup java -Dserver.port=8000 -Dcsp.sentinel.dashboard.server=localhost:8000 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.3.0.jar &
