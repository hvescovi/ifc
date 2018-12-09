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
    return render_template("listar-v6.html", lista = trabs)

@app.route("/form_inserir")
def form_inserir():
    alunos = Aluno.select()
    docentes = Docente.select()
    return render_template("form_incluir_v2.html", 
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

@app.route("/excluir")
def excluir():
    id_pi = request.args.get("id")
    TrabalhoPI.delete_by_id(id_pi)
    return listar()

@app.route("/form_alterar")
def form_alterar():
    # obter o identificador do PI a ser editado
    id_pi = request.args.get("id")
    # carregar as informações do PI
    pi = TrabalhoPI.get_by_id(id_pi)
    # carregar informações auxiliares
    alunos = Aluno.select()
    docentes = Docente.select()
    # desenhar o formulário de edição; deve-se
    # repassar ao formulário os dados do 
    # projeto integrador a ser editado
    # deve-se passar também os nomes dos alunos
    # e os nomes dos docentes, pra que os mesmos
    # possam ser escolhidos durante a edição
    return render_template("form_alterar_v4.html", 
        pi=pi, alunos=alunos, docentes=docentes)

@app.route("/alterar", methods=["post"])
def alterar():
    # obter ID do PI a ser alterado
    id_pi = request.form['id_pi']
    # obter novos aluno e docente
    # (talvez tenham sido alterados ou não)
    aluno = Aluno.get(id = request.form['aluno'])
    prof = Docente.get(id = request.form['docente'])
    # carregar o PI atual
    pi = TrabalhoPI.get_by_id(id_pi)
    # informar os novos valores
    pi.titulo = request.form['titulo']
    pi.descricao = request.form['descricao']
    pi.url = request.form['url']
    pi.alunos = [aluno]
    pi.docentes = [prof]
    # atualizar o PI
    pi.save()
    # encaminhar o fluxo para a tela de listagem
    return listar()

# método de alteração via execução de SQL
# http://docs.peewee-orm.com/en/latest/peewee/querying.html#updating-existing-records
@app.route("/alterar2", methods=["post"])
def alterar2():
    # obter ID do PI a ser alterado
    id_pi = request.form['id_pi']
    # obter novos aluno e docente
    # (talvez tenham sido alterados ou não)
    #aluno = Aluno.get(id = request.form['aluno'])
    #prof = Docente.get(id = request.form['docente'])
    # preparar o comando de execução SQL
    up = TrabalhoPI.update(titulo = request.form['titulo'],
         descricao = request.form['descricao'],
         url = request.form['url'],
         ).where(TrabalhoPI.id == id_pi)
    # executar o comando de atualização
    up.execute()
    # atualizar os atributos estrangeiros
    TrabalhoPI.get_by_id(id_pi).alunos = [Aluno.get_by_id(request.form['aluno'])]
    TrabalhoPI.get_by_id(id_pi).docentes = [Docente.get_by_id(request.form['docente'])]
    # encaminhar o fluxo para a tela de listagem
    return listar()

app.run(debug=True)