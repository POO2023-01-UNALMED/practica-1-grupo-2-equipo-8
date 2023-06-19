from abc import ABC, abstractmethod

class EstimuloEstudianteInterfaz(ABC):
  @abstractmethod
  def obtenerCriterios(self):
    pass

  @abstractmethod
  def obtenerAplicantes(self):
    pass

  @abstractmethod
  def verificarRequisitos(self, estudiante):
    pass
