#!/usr/bin/env python
from subprocess import call
from xml.dom import minidom

def start():
        """Correr la progra"""
        fo = open("grafo.xml","r+")
        doc = minidom.parse(fo)
        conexiones = doc.getElementsByTagName("conexion")
        dispositivos= doc.getElementsByTagName("dispositivo")
        for dispositivo in dispositivos:
            id= (dispositivo.getAttribute("id"))
            tipo= dispositivo.getAttribute("tipo")
            puerto= (dispositivo.getAttribute("puerto"))
            print "java -jar ~/cedms.jar "+id+" "+puerto+" "+tipo
            call(["xterm", '-e', "java -jar ~/cedms.jar "+id+" "+puerto+" "+tipo])#abre una consola con el comando indicado

if __name__ == '__main__':
    start()