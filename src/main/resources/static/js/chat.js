(function($) {
    var stompClient = null;

    function setConnected(connected) {
        document.getElementById("connect").disabled = connected;
        document.getElementById("disconnect").disabled = !connected;
        document.getElementById("conversationDiv").style.visibility = connected ? "visible" : "hidden";
        $("#response").html();
    }

    function connect() {
        var socket = new SockJS("/wso"); //1.连接SockJS的endpoint名称为/endpointQuickcodes
        stompClient = Stomp.over(socket);//2.使用WebSocket子协议的STOMP客户端
        stompClient.connect({}, function(frame) {//3.连接WebSocket服务端
            setConnected(true);
            console.log("Connected: " + frame);
            stompClient.subscribe("/wso/getResponse", function(respnose){ //4.通过stompClient.subscribe订阅/topic/getResponse目标(destination)发送的消息，这个是在控制器的@SendTo中定义的。
                showResponse(JSON.parse(respnose.body).responseMessage);
            });
        });
    }


    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendName() {
        var name = $("#name").val();
        //5.通过stompClient.send向/welcome目标发送消息，这个是在控制器的@MessageMapping中定义的。
        stompClient.send("/welcome", {}, JSON.stringify({ "name": name }));
    }

    function showResponse(message) {
        var response = $("#response");
        response.html(message);
    }
})(jQuery);
