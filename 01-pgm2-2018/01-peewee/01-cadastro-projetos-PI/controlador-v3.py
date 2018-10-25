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

@app.route("/form_inserir")
def form_inserir():
    alunos = Aluno.select()
    docentes = Docente.select()
    return render_template("form_incluir_v1.html", 
        alunos=alunos, docentes=docentes)
app.run(debug=True)