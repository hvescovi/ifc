from flask import Flask, render_template
from modelo import *

app =  Flask(__name__)

@app.route("/")
def inicio():
    return "Sistema de cadastro de projetos "+\
    "integradores. <a href=/listar>"+\
    "Clique aqui</a> para começar."

@app.route("/listar")
def listar():
    trabs = TrabalhoPI.select()
    return render_template("listar-v2.html", lista = trabs)

app.run()