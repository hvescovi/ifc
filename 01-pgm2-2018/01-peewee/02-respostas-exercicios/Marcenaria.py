import os, datetime
from peewee import *

arq = 'marcenaria.db'
db = SqliteDatabase(arq)

class BaseModel(Model):
    class Meta:
        database = db     

class Cliente(BaseModel):
    nome = CharField()
    email = CharField()
    telefone = CharField()
    
class Material(BaseModel):
    nome = CharField()
    estoque = IntegerField()

class Pedido(BaseModel):
    data = DateTimeField(default=datetime.datetime.now)
    cliente = ForeignKeyField(Cliente)

class Mobilia(BaseModel):
    nome = CharField()
    materiais = ManyToManyField(Material)
    cor = CharField()
    pedido = ForeignKeyField(Pedido)
  
if __name__ == "__main__":
    if os.path.exists(arq):
        os.remove(arq)
    db.connect()
    db.create_tables([
        Cliente, Material, Pedido, Mobilia, 
        Mobilia.materiais.get_through_model()])

    cliente = Cliente.create(nome="Joao", 
        email="joao@gmail.com", telefone="47-9291-1234")
    madeira = Material.create(nome = "Madeira", estoque = 5)
    vidro = Material.create(nome = "Vidro", estoque = 3)
    pedido = Pedido.create(cliente = cliente)
    mob1 = Mobilia.create(nome="Mesa triangular, centro de vidro",
        cor="azul", pedido = pedido)
    mob1.materiais.add(madeira)
    mob1.materiais.add(vidro)
    mob2 = Mobilia.create(nome="4 cadeiras de madeira",
        pedido = pedido, cor="natural")
    mob2.materiais.add(madeira)        
    print("cliente: "+pedido.cliente.nome)
    mobs = Mobilia.select().where(Mobilia.pedido==pedido)
    for mob in mobs:
        print("mobília: "+mob.nome)
        for mat in mob.materiais:
            print("* "+mat.nome)