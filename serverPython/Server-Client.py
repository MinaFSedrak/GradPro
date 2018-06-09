import socket
import threading

serverIP = '192.168.1.28'
serverPORT = 7755

serverAddress = (serverIP, serverPORT)

clientIP = '192.168.1.19'
clientPORT = 7801

clientAddress = (clientIP, clientPORT)

class reciver(threading.Thread):

    def run(self):
        server = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        server.bind(serverAddress)
        server.listen(5)

        print ("Starts listening On ",serverIP,":",serverPORT)

        while True:
            client, addr = server.accept()
            data = str(client.recv(1024))
            print ("Receved: " + data)

            t2 = sender()
            t2.start()

class sender(threading.Thread):

    def run(self):
        client = socket.socket()

        client.connect(clientAddress)
        msg = input("Enter msg: ")
        client.send(msg.encode('utf-8'))
        client.close()



t1 = reciver()
t1.start()