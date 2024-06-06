package com.culysoft.imc;

import java.util.Comparator;
import java.util.List;

public class CalcularIMC {

    private static final double IMC_NORMAL_INICIO = 18.5;
    private static final double IMC_NORMAL_FIM = 25.0;

    public static boolean estaRecomendavelIMCAlguemComIdadeAcimaDe20Anos(double peso, double altura) {
        if (altura == 0.0) throw new RuntimeException("Altura nao pode ser nulo");

        double imc = peso / (altura * altura);

        if (imc > IMC_NORMAL_INICIO && imc < IMC_NORMAL_FIM)
            return true;

        return false;
    }

    public static Pessoa encontrarPessoaComPiorIMC(List<Pessoa> pessoas) {
        return pessoas.stream().sorted(Comparator.comparing(CalcularIMC::imc))
                .reduce((primeiro, segundo) -> segundo).orElse(null);
    }

    public static double[] encontrarListaDeIMC(List<Pessoa> pessoas) {
        double[] imcs = new double[pessoas.size()];

        for(int i = 0; i < pessoas.size(); i++) {
            imcs[i] = CalcularIMC.imc(pessoas.get(i));
        }

        return imcs;
    }

    private static double imc(Pessoa pessoa) {
        if (pessoa.getAltura() == 0.0) throw new RuntimeException("Altura nao pode ser nulo");

        if (pessoa.getIdade() < 20)
            pessoa.setObs("Invalido porque a idade eh inferior");

        double resultado = pessoa.getPeso() / (pessoa.getAltura() * pessoa.getAltura());
        return Math.round(resultado * 100) / 100;
    }
}
