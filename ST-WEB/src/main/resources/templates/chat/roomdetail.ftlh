<!doctype html>
<html lang="en">
  <head>
    <title>Websocket ChatRoom</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css">
    <style>
      [v-cloak] {
          display: none;
      }
    </style>
  </head>
  <body>
    <div class="container" id="app" v-cloak>
        <div class="row">
            <div class="col-md-6">
                <h4>{{roomName}} <span class="badge badge-info badge-pill">{{userCount}}</span></h4>
            </div>
            <div class="col-md-6 text-right">
                <a class="btn btn-primary btn-sm" href="/logout">로그아웃</a>
                <a class="btn btn-info btn-sm" href="/chat/room">채팅방 나가기</a>
            </div>
        </div>
        <div class="input-group">
            <div class="input-group-prepend">
                <label class="input-group-text">내용</label>
            </div>
            <input type="text" class="form-control" v-model="message" v-on:keypress.enter="sendMessage('TALK')">
            <div class="input-group-append">
                <button class="btn btn-primary" type="button" @click="sendMessage('TALK')">보내기</button>
            </div>
        </div>
        <ul class="list-group">
            <li class="list-group-item" v-for="message in messages">
                {{message.sender}} - {{message.message}}</a>
            </li>
        </ul>
    </div>
    <!-- JavaScript -->
    <script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
    <script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
    <script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>
    <script>
    	var roomid = window.location.href.split("/")[6];
        //alert(document.title);
        // websocket & stomp initialize
        var sock = new SockJS("/ws");
        var ws = Stomp.over(sock);
        var reconnect = 0;
        // vue.js
        var vm = new Vue({
            el: '#app',
            data: {
                roomId: '',
                room: {},
                roomName: '',
                sender: '',
                message: '',
                messages: [],
                userCount: 0
            },
            created() {
                this.roomId = roomid;
                this.findRoom();
                this.setSender();
            },
            methods: {
            	setSender: function(){
            		axios.get('/chat/info').then(response => {
            			this.roomName = response.data.roomName;
		                this.sender = response.data.memberId;
		            });
            	},
                findRoom: function() {
                    axios.get('/chat/room/'+this.roomId).then(response => { this.room = response.data; });
                },
                sendMessage: function(type) {
                    ws.send("/pub/chat/message", {}, JSON.stringify({type:type, roomId:this.roomId, sender:this.sender, message:this.message}));
                    this.message = '';
                },
                recvMessage: function(recv) {
                	this.userCount = recv.userCount;
                    this.messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message})
                }
            }
        });

        function connect() {
            // pub/sub event
            ws.connect({}, function(frame) {
                ws.subscribe("/sub/chat/room/"+vm.$data.roomId, function(message) {
                    var recv = JSON.parse(message.body);
                    vm.recvMessage(recv);
                });
                ws.send("/pub/chat/message", {}, JSON.stringify({type:'ENTER', roomId:vm.$data.roomId, sender:vm.$data.sender}));
            }, function(error) {
                if(reconnect++ < 5) {
                    setTimeout(function() {
                        // console.log("connection reconnect");
                        sock = new SockJS("/ws");
                        ws = Stomp.over(sock);
                        connect();
                    },10*1000);
                }
            });
        }
        connect();
    </script>
  </body>
</html>