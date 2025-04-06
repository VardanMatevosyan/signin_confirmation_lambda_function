#!/bin/bash

BRANCH_NAME=${1:-$CIRCLE_BRANCH}

if [[ "$BRANCH_NAME" == "main" ]]; then
  echo "export STAGE=dev" > env-vars.sh # dev is for starting point, change this to prod when first dev branch would be created
elif [[ "$BRANCH_NAME" == "staging" ]]; then
  echo "export STAGE=staging" > env-vars.sh
else
  echo "export STAGE=dev" > env-vars.sh
fi
