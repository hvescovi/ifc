import os
from modelo import *

class Dao:

    @staticmethod
    def inicializar():
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