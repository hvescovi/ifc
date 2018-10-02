import os
from peewee import *

arq = '50-exemplo-manytomany-estilo1.db'
db = SqliteDatabase(arq)
    
class Aluno(Model):
    nome = CharField()
    class Meta:
        database = db
    
class Disciplina(Model):
    nome = CharField()
    class Meta:
        database = db

class AlunoDisciplina(Model):
    aluno = ForeignKeyField(Aluno)
    disciplina = ForeignKeyField(Disciplina)
    class Meta:
        database = db
    #    primary_key = CompositeKey('aluno', 'disciplina')
    # usar force_insert=True ao salvar entidade manyTomany, caso a chave primária seja composta
    # http://docs.peewee-orm.com/en/latest/peewee/models.html#non-integer-primary-keys-composite-keys-and-other-tricks

if os.path.exists(arq):
    os.remove(arq)
db.connect()
db.create_tables([
    Aluno,
    Disciplina,
    AlunoDisciplina])

joao = Aluno(nome = "Joao da Silva")
joao.save()

ingles = Disciplina(nome = 'Inglês')
ingles.save()

espanhol = Disciplina.create(nome = 'Espanhol')

disciplina1Joao = AlunoDisciplina(aluno=joao, disciplina=ingles)
disciplina1Joao.save() # force_insert=True se usar chave composta
disciplina2Joao = AlunoDisciplina.create(aluno=joao, disciplina=espanhol)

maria = Aluno(nome = 'Maria')
maria.save()

# com 'create' não é preciso usar force_insert=True com uso da chave primária
disciplinaMaria = AlunoDisciplina.create(aluno=maria, disciplina=ingles)

q1 = AlunoDisciplina.select()
for dado in q1:
    print(dado.aluno.nome+" cursa: "+dado.disciplina.nome)

q2 = AlunoDisciplina.select().where(AlunoDisciplina.aluno == (Aluno.get(Aluno.nome == 'Joao da Silva')))
print("Disciplinas de Joao:")
for dado2 in q2:
    print(dado2.disciplina.nome)