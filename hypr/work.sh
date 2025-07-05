#!/bin/bash

while true; do

  findAegi=$(eval hyprctl clients -j | jq '.[] | select(.class == "aegisub" and .focusHistoryID == 0)')

  if [[ -n "$findAegi" ]]; then
    hyprctl keyword binde , F, exec, wtype "FFFFFFF"
  else
    hyprctl keyword unbind , F
  fi

  sleep 1

done
