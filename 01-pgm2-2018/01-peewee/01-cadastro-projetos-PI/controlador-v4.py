from flask import Flask, render_template, request
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
    return render_template("listar-v3.html", lista = trabs)

@app.route("/form_inserir")
def form_inserir():
    alunos = Aluno.select()
    docentes = Docente.select()
    return render_template("form_incluir_v1.html", 
        alunos=alunos, docentes=docentes)

@app.route("/inserir",methods=["post"])
def inserir():
    aluno = Aluno.get(id = request.form['aluno'])
    prof = Docente.get(id = request.form['docente'])

    t1 = TrabalhoPI.create(titulo = request.form['titulo'],
        descricao = request.form['descricao'],
        url = request.form['url'])
    t1.alunos.add(aluno)
    t1.docentes.add(prof)
    return listar()

app.run(debug=True)