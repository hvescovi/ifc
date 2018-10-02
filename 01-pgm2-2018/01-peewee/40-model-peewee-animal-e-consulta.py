import peewee, os

# tipos de dados:
# http://docs.peewee-orm.com/en/latest/peewee/models.html#fields

db = peewee.SqliteDatabase('animalia.db')

class Animal(peewee.Model):
    nomedono = peewee.CharField()
    tipo_animal = peewee.CharField()
    raca = peewee.CharField()
    
    class Meta:
        database = db
    def __str__(self):
        return self.tipo_animal+","+self.raca+\
        " de "+self.nomedono+""

class Consulta(peewee.Model):
    data = peewee.CharField()
    servico = peewee.CharField()
    horario = peewee.CharField()
    animal = peewee.ForeignKeyField(Animal)
    confirma = peewee.CharField()
    myID = peewee.CharField()
    class Meta:
        database = db
    def __str__(self):
        return self.servico+" em "+self.data+\
        ":"+self.horario+", confirmado: "+\
        self.confirma+", ID da consulta: "+\
        self.myID+" | animal: "+str(self.animal)

if __name__ == '__main__':
    arq = 'animalia.db'
    if os.path.exists(arq):
        os.remove(arq)

    # inicialização
    try:
        db.connect()
        # cria tabelas com colunas, índices, chaves estrangeiras
        db.create_tables([Animal,Consulta]) 
    except peewee.OperationalError as e:
        print("erro ao criar tabelas: "+str(e))

    print("TESTE DO ANIMAL")
    a1 = Animal(nomedono="José", 
    tipo_animal="C", raca="Chiuaua")
    print(a1)
    print("TESTE DA CONSULTA")
    c1 = Consulta(data="19/09/2018", 
    servico="Consulta de rotina", 
    horario="14:00", animal=a1, 
    confirma="N", myID="c9d8f7gu4h3hnwsik3e")
    print(c1)

    print("teste do DAO")
    a1.save()
    c1.save()
    c2 = Consulta(data="21/09/2018", 
    servico="Aplicação de vacina", 
    horario="10:00", animal=a1, 
    confirma="S", myID="d9firtu3434uit")
    c2.save()
    todos = Consulta.select().get()
    print(todos)    