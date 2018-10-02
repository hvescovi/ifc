import os
from peewee import *

arq = '51-exemplo-manytomany-estilo2.db'
db = SqliteDatabase(arq)

class BaseModel(Model):
    class Meta:
        database = db
        
class Aluno(BaseModel):
    nome = CharField()

class Disciplina(BaseModel):
    nome = CharField()
    alunos = ManyToManyField(Aluno) 
    # personalizar nome da tabela n-n: backref='disciplinas_do_aluno')
    # https://peewee.readthedocs.io/en/latest/peewee/api.html#ManyToManyField

if os.path.exists(arq):
        os.remove(arq)
db.connect()
db.create_tables([
    Aluno,
    Disciplina,
    Disciplina.alunos.get_through_model()])

joao = Aluno(nome = "Joao da Silva")
joao.save()

ingles = Disciplina(nome = 'Inglês')
ingles.save()

espanhol = Disciplina.create(nome = 'Espanhol')

joao.disciplinas.add([ingles, espanhol])

maria = Aluno(nome = 'Maria')
maria.save()

maria.disciplinas.add([espanhol])

todos = Disciplina.select()
for disc in todos:
    print("Quem cursa a disciplina:"+disc.nome)
    for aluno in disc.alunos:
         print(aluno.nome)

print("Disciplinas de Joao:")
for disciplina in joao.disciplinas:
    print(disciplina.nome)