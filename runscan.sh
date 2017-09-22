if [ -e releaseversion.txt ]
then
    if read -r RN < releaseversion.txt
    then
        RN="$(echo -e "${RN}" | tr -d '[:space:]')"
        echo -n $RN
        if [[ $RN ]]
            then PRODUCT_RELEASE=$RN
        fi
    fi
fi

