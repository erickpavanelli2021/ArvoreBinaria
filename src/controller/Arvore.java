package controller;

class Arvore{
	private No raiz;
	
	public Arvore() {
		raiz = null;	
	}
	
	public void inserir (long valor) {
		No novo = new No();
		novo.elemento = valor;
		novo.filhoDireito = null;
		novo.filhoEsquerdo = null;
		
		if (raiz == null) {
			raiz = novo;
		}else {
			No atual = raiz;
			No anterior;
			do {
				anterior = atual;
				if( valor <= atual.elemento) { //ir para esquerda
					atual = atual.filhoEsquerdo;
					if (atual == null) {
						anterior.filhoEsquerdo = novo;
						return;
					}
				}else { //ir para direita
					atual = atual.filhoDireito;
					if (atual == null) {
						anterior.filhoDireito = novo;
						return;
					}
				}
			}while (true);
		}
	}
	
	public No buscar(long chave) {
		if (raiz == null) {
			return null; //se arvore vazia
		}
		No atual = raiz;
		while (atual.elemento != chave) {
			if (chave < atual.elemento) {
				atual = atual.filhoEsquerdo; //caminha para esquerda
			}else {
				atual = atual.filhoDireito; //caminha para direita
			}
			if (atual == null) {
				return null; //encontrou uma folha
			}
		}
		return atual;
	}
	
	public boolean remover(long valor) {
		if (raiz == null) {
			return false;
		}
		
		No atual = raiz;
		No pai = raiz;
		boolean isFilhoEsq = true;
		
		while (atual.elemento != valor) { //laço while de busca do valor
			pai = atual;
			if (valor < atual.elemento) {//caminha para esquerda
				atual = atual.filhoEsquerdo;
				isFilhoEsq = true;
			}else {//caminha para direita
				atual = atual.filhoDireito;
				isFilhoEsq = false;
			}
			if (atual == null) {
				return false; // encontrou uma folha
			}
		}
// se chegou aqui quer dizer que encontrou o valor "atual": contem a referencia ao No a ser eliminado "pai": contem
// a referencia para o pai do No a ser eliminado "isFilhoEsq": é verdadeiro se atual é filho a esqueda do pai
		
		//Se não possui nenhum filho (é uma folha), elimine-o
		if (atual.filhoEsquerdo == null && atual.filhoDireito == null) {
			if(atual == raiz) {
				raiz = null;
			}else if (isFilhoEsq) {
				pai.filhoEsquerdo = null;
			}else {
				pai.filhoDireito = null;
			}
		}
		
		//Se é pai e não possui um filho a direita, substitui pela sub-arvore a direita
		else if (atual.filhoDireito == null) {
			if (atual == raiz) {
				raiz = atual.filhoEsquerdo; //se raiz
			}else if (isFilhoEsq) {
				pai.filhoEsquerdo = atual.filhoEsquerdo; //se for filho a esquerda do pai
			}else {
				pai.filhoDireito = atual.filhoEsquerdo; //se for filho a direita do pai
			}
		}
		
		//Se é pai e não possui um filho a esquerda, substitui pela sub-arvore a esquerda
		else if (atual.filhoEsquerdo == null) {
			if (atual == raiz) {
				raiz = atual.filhoDireito; // se raiz
			}else if (isFilhoEsq) {
				pai.filhoEsquerdo = atual.filhoDireito; // se for filho a esquerda do pai
			}else {
				pai.filhoDireito = atual.filhoDireito; // se for filho a direita do pai
			}
		}
		
		//Se possui mais de um filho, se for um avô ou outro grau maior de parentesco
		else {
			No sucessor = no_sucessor(atual);
			//Usando sucessor que seria o Nó mais a esquerda da sub-arvore a direita do No
			//que deseja-se remover
			if (atual == raiz) 
				raiz = sucessor; // se raiz
			else if (isFilhoEsq) {
				pai.filhoEsquerdo = sucessor; // se for filho a esquerda do pai
			}else {
				pai.filhoDireito = sucessor; //se for filho a direita do pai
			}
			sucessor.filhoEsquerdo = atual.filhoEsquerdo; // acertando o ponteiro a esquerda do sucessor agora que ele assumiu a posição correta na arvore
		}
		return true;
	}
	
	//O sucessor é o Nó mais a esquerda da sub-arvore a direita do Nó que foi passado como parametro do metodo
	public No no_sucessor(No apaga) {
		No paidosucessor = apaga;
		No sucessor = apaga;
		No atual = apaga.filhoEsquerdo; //vai para a sub-arvore a direita
		
		while (atual != null) {//enquanto não chegar no Nó mais a esquerda
			paidosucessor = sucessor;
			sucessor = atual;
			atual = atual.filhoEsquerdo; //caminha para a esquerda
		}
		if (sucessor != apaga.filhoDireito) { // se sucessor não é o filho a direita do Nó que deverá ser eliminado
		paidosucessor.filhoEsquerdo = sucessor.filhoDireito;
		sucessor.filhoDireito = apaga.filhoDireito;
	}
		return sucessor;
}
	
	public void caminhar() {
		System.out.print("\n Exibindo em ordem: ");
		emOrder(raiz);
		System.out.print("\n Exibindo em pos-ordem: ");
		posOrder(raiz);
		System.out.print("\n Exibindo em pre-ordem: ");
		preOrder(raiz);
		System.out.print("\n Altura da arvore: "+altura(raiz));
		System.out.print("\n Quantidade de folhas: "+folhas(raiz));
		System.out.print("\n Quantidade de Nós: "+contarNos(raiz));
		if (raiz != null) {
			System.out.println("\n Valor minimo: "+min().elemento);
			System.out.println("\n Valor maximo: "+max().elemento);
		}
	}
	
	public void emOrder(No atual) {
		if (atual != null) {
			emOrder(atual.filhoEsquerdo);
			System.out.println(atual.elemento +" ");
			emOrder(atual.filhoDireito);
		}
	}
	
	public void preOrder (No atual) {
		if (atual != null) {
			System.out.println(atual.elemento + " ");
			preOrder(atual.filhoEsquerdo);
			preOrder(atual.filhoDireito);
		}
	}
	
	public void posOrder (No atual) {
		if (atual != null) {
			posOrder(atual.filhoEsquerdo);
			posOrder(atual.filhoDireito);
			System.out.println(atual.elemento + " ");
		}
	}
	
	public int altura (No atual) {
		if (atual == null || (atual.filhoEsquerdo == null && atual.filhoDireito == null)) {
			return 0;
		}else {
			if (altura(atual.filhoEsquerdo) > altura(atual.filhoDireito)) {
				
				return (1 + altura(atual.filhoEsquerdo));
			}else {
				return (1 + altura(atual.filhoDireito));
			}
		}
	}
	
	public int folhas (No atual) {
		if (atual == null) {
			return 0;
		}
		if (atual.filhoEsquerdo == null && atual.filhoDireito == null) {
			return 1;
		}
		return folhas(atual.filhoEsquerdo) + folhas(atual.filhoDireito);
	     }
	
	public int contarNos(No atual) {
		if (atual == null) {
			return 0;
		}else {
			return (1 + contarNos(atual.filhoEsquerdo) + contarNos(atual.filhoDireito));
		}
	}
	
	public No min() {
		No atual = raiz;
		No anterior = null;
		while (atual != null) {
			anterior = atual;
			atual = atual.filhoEsquerdo;
		}
		return anterior;
	}
	
	public No max() {
		No atual = raiz;
		No anterior = null;
		while (atual !=null) {
			anterior = atual;
			atual = atual.filhoDireito;
		}
		return anterior;
	}
	
	public void print() {
		print("", raiz, false);
	}
	
	public void print(String prefix, No no, boolean isEsquerdo) {
		if (no != null) {
			System.out.println(prefix + (isEsquerdo ? "|-- " : "\\--") + no.elemento);
			print(prefix + (isEsquerdo ? "|   " : "    "), no.filhoEsquerdo, true);
			print(prefix + (isEsquerdo ? "|   " : "    "), no.filhoDireito, false);
		}
	}
	
}	
	
	
	
	
	
	
	
	
	
	
	
	