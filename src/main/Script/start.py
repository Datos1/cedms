#!/usr/bin/env python
from subprocess import call
from xml.dom import minidom
import threading

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
            print "java -jar ~/IdeaProjects/cedms/out/artifacts/cedms_jar/cedms.jar "+id+" "+puerto+" "+tipo
            listaConexiones = []
            for conexion in conexiones:
                if conexion.getAttribute("source") == id:
                    for dispositivo in dispositivos:
                        if dispositivo.getAttribute("id") == conexion.getAttribute("source"):
                            listaConexiones.append(dispositivo.getAttribute("puerto"))
            threading.Thread(target=java, args=(id,puerto,tipo,listaConexiones)).start()
def java(id,puerto,tipo,listaPuertos):
    call(["xterm", '-e', "java -jar ~/IdeaProjects/cedms/out/artifacts/cedms_jar/cedms.jar "+id+" "+puerto+" "+tipo]),()

if __name__ == '__main__':
    start()