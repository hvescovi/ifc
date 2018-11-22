import os
from peewee import *

arq = 'livros.db'
db = SqliteDatabase(arq)

class BaseModel(Model):
    class Meta:
        database = db     

class Livro(BaseModel):
    titulo = CharField()
    autor = CharField()
    ano = CharField()
    
class Exemplar(BaseModel):
    codigo_de_barras = CharField()
    numero = CharField()
    livro = ForeignKeyField(Livro)
   
if __name__ == "__main__":
    if os.path.exists(arq):
        os.remove(arq)
    db.connect()
    db.create_tables([Livro, Exemplar])

    python = Livro.create(titulo = "Introdução a Python",
        autor = "Nilo Ney", ano = "2010")
    py1 = Exemplar.create(codigo_de_barras = "139953421",
        livro = python, numero = "1")
    print(python.titulo)
    print(py1.codigo_de_barras, py1.livro.autor)