from flask import Flask, render_template

app =  Flask(__name__)

@app.route("/")
def inicio():
    return "Sistema de cadastro de projetos "+\
    "integradores. <a href=/listar>"+\
    "Clique aqui</a> para começar."

@app.route("/listar")
def listar():
    return render_template("listar.html")

app.run()