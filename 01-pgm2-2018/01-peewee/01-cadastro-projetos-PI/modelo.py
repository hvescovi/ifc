import os
from peewee import *

arq = 'pi3.db'
db = SqliteDatabase(arq)

class BaseModel(Model):
    class Meta:
        database = db
        
class Aluno(BaseModel):
    nome = CharField()
    turma = CharField()

class Area(BaseModel):
    nome = CharField()
    descricao = CharField()
    def __str__(self):
        return self.nome
    
class Docente(BaseModel):
    nome = CharField()
    areas = ManyToManyField(Area)
    def __str__(self):
        s = self.nome
        for area in self.areas:
            s += ", atuante em "+str(area)
        return s

class TrabalhoPI(BaseModel):
    titulo = CharField()
    descricao = CharField()
    url = CharField()
    alunos = ManyToManyField(Aluno)
    docentes = ManyToManyField(Docente)
    def __str__(self):
        s = self.titulo
        for aluno in self.alunos:
            s+= ", membro: "+ aluno.nome
        for doc in self.docentes:
            s += ", orientado por: " + str(doc)
        return s

if __name__ == "__main__":

    if os.path.exists(arq):
        os.remove(arq)
    db.connect()
    db.create_tables([
        Aluno,
        Area,
        Docente,
        Docente.areas.get_through_model(),
        TrabalhoPI,
        TrabalhoPI.alunos.get_through_model(),
        TrabalhoPI.docentes.get_through_model()])

    pedro = Aluno.create(nome = "Pedro de Oliveira", 
        turma = "301 INFO")
    maria = Aluno.create(nome = "Maria de Souza", 
        turma = "301 INFO")

    bd = Area.create(nome = "Banco de dados",
        descricao = "Modelagem, implementação e "+\
        "suporte de bancos de dados.")
    tf = Area.create(nome = "Tolerância a faltas", 
        descricao = "Provimento de robustez a sistemas, "+\
        "para que os mesmos operem de maneira ininterrupta.")
    
    aldelir = Docente.create(nome = "Aldelir Luiz")
    aldelir.areas.add(bd)
    aldelir.areas.add(tf)

    t1 = TrabalhoPI.create(titulo = "Bancos de Dados em "+\
        "Grafos para Modelagem Tridimensional de Estrelas",
        descricao = "Uso do Neo4j para armazenar "+\
        "pontos da estrela Órion",
        url = "não disponível")
    t1.alunos.add(pedro)
    t1.alunos.add(maria)
    t1.docentes.add(aldelir)

    print(t1)    