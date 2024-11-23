package med.voll.api.enums;

public enum Especialidade {
	
	ORTOPEDIA(1),
	CARDIOLOGIA(2),
	GINECOLOGIA(10),
	DERMATOLOGIA(11);
	
	private int setor;
	
	private Especialidade(int valor) {
		this.setor = valor;
	}
	
	//retorna a especialidade por setor
	public static Especialidade buscaEspecialidadePorSetor(int setorP) {
		for(Especialidade e : Especialidade.values()) {
			if(setorP == e.setor) {
				return e;
			}
		}
		return null;
	}

}
