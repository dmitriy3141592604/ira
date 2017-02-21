start
end
id-start
id-rest

start     iSpace          start      nothing
start     isEof           end        nothing
start     isIdStartChar   idStart    push
idStart   isIdChar        idRest     push
idRest    isIdChar        idRest     push
idRest    isSpace         start      pop
idRest    isEof           end        pop
