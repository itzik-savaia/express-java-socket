
exports.addListenersToSocketAndUpdateTables = (io) => {

    io.on("connection", (socket) => {

        socket.on("hello", (arg) => {
            console.log("hello: " + arg);
        });
        socket.emit('hello', "socket event ");


        socket.on("stockPosition", (arg) => {
            console.log("stockPosition: " + arg);
        })
        socket.emit('stockPosition', "socket stockPosition ");

    });
}
