from abc import ABC, abstractmethod

class EstimuloProfesorInterfaz(ABC):
  @abstractmethod
  def obtenerCriterios(self):
    pass

  @abstractmethod
  def obtenerAplicantes(self):
    pass

  @abstractmethod
  def verificarRequisitos(self, profesor):
    pass
