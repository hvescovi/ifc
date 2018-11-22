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

class Mobilia(BaseModel):
    nome = CharField()
    materiais = ManyToManyField(Material)
    cor = CharField()

class Pedido(BaseModel):
    data = DateTimeField(default=datetime.datetime.now)
    cliente = ForeignKeyField(Cliente)
    mobilia = ForeignKeyField(Mobilia)
    
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
    mob1 = Mobilia.create(nome="Mesa triangular, centro de vidro",
        cor="azul")
    mob1.materiais.add(madeira)
    mob1.materiais.add(vidro)
    pedido1 = Pedido.create(cliente=cliente, mobilia=mob1)
    
    mob2 = Mobilia.create(nome="4 cadeiras de madeira",
        cor="natural")
    mob2.materiais.add(madeira)        
    pedido2 = Pedido.create(cliente=cliente, mobilia=mob2)
    
    pedidos = [pedido1, pedido2]
    for pd in pedidos:
        print("cliente: "+pd.cliente.nome)
        print("mobília: "+pd.mobilia.nome)
        for mat in pd.mobilia.materiais:
            print("* "+mat.nome)