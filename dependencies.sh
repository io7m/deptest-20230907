#!/bin/bash
./gradlew :one.irradia.mime.vanilla:dependencies \
  | sed -n 's/.*--- \([^ ]*\).*/\1/p' \
  | grep -v "^project$" \
  | sort \
  | uniq
