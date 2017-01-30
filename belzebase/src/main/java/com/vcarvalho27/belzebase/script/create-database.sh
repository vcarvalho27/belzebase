#!/bin/bash

VERSIONS_FOLDER="dbversiongen"
MODELS_FOLDER="model"

function initScript
{

    echo "
    package appcompras.jcom.com.br.appcompras.dbversiongen;
    import java.util.List;
    import appcompras.jcom.com.br.database.IDatabaseVersion;

    public class version_0000001  implements IDatabaseVersion {

        @Override
        public List<String> getScriptList() {
            return null;
        }
    }
    " > $1/$VERSIONS_FOLDER/version_0000001.java

}

if [ "$1" != "" -a "$2" != "" ]; then

    if [ ! -d $1/$VERSIONS_FOLDER ]; then
      mkdir $1/$VERSIONS_FOLDER;
    fi

    initScript $1 $2
else
    echo "Deve ser informado um parametro com o path da aplicação e um parametro com o nome do pacote dos modelos"
fi


sleep 5

