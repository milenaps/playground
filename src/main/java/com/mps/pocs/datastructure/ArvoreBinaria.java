package com.mps.pocs.datastructure;

public class ArvoreBinaria {

    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    /*
     * Dada uma árvore binária com nós já inseridos, um nó é da esquerda festiva se ele for filho esquerdo de algum outro nó
     */
    public int contarNosDaEsquerdaFestiva(No no, int somatoria) {
        if (no.getEsq() != null) {
            ++somatoria;
            somatoria = contarNosDaEsquerdaFestiva(no.getEsq(), somatoria);
        }
        if (no.getDir() != null) {
            somatoria = contarNosDaEsquerdaFestiva(no.getDir(), somatoria);
        }
        return somatoria;
    }

    /*
     * Imprime os ids de uma árvore binária com recuos de margem proporcionais ao nível do nó
     */
    public String imprimirRecuosMargemProporcionaisAoNivelDoNo() {
        StringBuilder sb = new StringBuilder(raiz.getId() + "\n");
        popularRecuosMargemProporcionaisAoNivelDoNo(sb, raiz, 3);
        return sb.toString();
    }

    private void popularRecuosMargemProporcionaisAoNivelDoNo(StringBuilder sb, No no, int margem) {
        String recuo = String.format("%-" + margem + "s", " ");
        if (no.getEsq() == null) sb.append(recuo + "-\n");
        else {
            sb.append(recuo);
            sb.append(no.getEsq().getId());
            sb.append("\n");

            popularRecuosMargemProporcionaisAoNivelDoNo(sb, no.getEsq(), margem + 3);
        }

        if (no.getDir() == null) sb.append(recuo + "-\n");
        else {
            sb.append(recuo);
            sb.append(no.getDir().getId());
            sb.append("\n");

            popularRecuosMargemProporcionaisAoNivelDoNo(sb, no.getDir(), margem + 3);
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();

        System.out.println("==== CENARIO 1\n");
        arvoreBinaria.raiz = new No(100,
                new No(200,
                        new No(400, null, null),
                        new No(500,
                                new No(800,
                                        null,
                                        new No(112, null, null)
                                ),
                                null
                        )
                ),
                new No(300,
                        new No(600,
                                new No(900, null, null), new No(110, null, null)),
                        new No(700,
                                null, new No(111, null, null)
                        )
                )
        );
        System.out.println(String.format("Total de nós da esquerda festiva: %s\n", arvoreBinaria.contarNosDaEsquerdaFestiva(arvoreBinaria.raiz, 0)));
        System.out.println(arvoreBinaria.imprimirRecuosMargemProporcionaisAoNivelDoNo());

        System.out.println("\n==== CENARIO 2\n");
        arvoreBinaria.raiz = new No(555,
                new No(333, new No(111, null, null), new No(444, null, null)),
                new No(888, null, new No(999, null, null))
        );
        System.out.println(String.format("Total de nós da esquerda festiva: %s\n", arvoreBinaria.contarNosDaEsquerdaFestiva(arvoreBinaria.raiz, 0)));
        System.out.println(arvoreBinaria.imprimirRecuosMargemProporcionaisAoNivelDoNo());
    }
}

class No {
    private long id;
    private No esq, dir;

    public No(long id, No esq, No dir) {
        this.id = id;
        this.esq = esq;
        this.dir = dir;
    }

    public long getId() {
        return id;
    }

    public No getEsq() {
        return esq;
    }

    public No getDir() {
        return dir;
    }
}