package br.com.bytebank.banco.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.bytebank.banco.modelo.Cliente;
import br.com.bytebank.banco.modelo.Conta;
import br.com.bytebank.banco.modelo.ContaCorrente;
import br.com.bytebank.banco.modelo.ContaPoupanca;

public class TesteOrderList {

	public static void main(String[] args) {

        Conta cc1 = new ContaCorrente(22, 33);
        Cliente clienteCC1 = new Cliente();
        clienteCC1.setNome("Nico");
        cc1.setTitular(clienteCC1);
        cc1.deposita(333.0);

        Conta cc2 = new ContaPoupanca(22, 44);
        Cliente clienteCC2 = new Cliente();
        clienteCC2.setNome("Guilherme");
        cc2.setTitular(clienteCC2);
        cc2.deposita(444.0);

        Conta cc3 = new ContaCorrente(22, 11);
        Cliente clienteCC3 = new Cliente();
        clienteCC3.setNome("Paulo");
        cc3.setTitular(clienteCC3);
        cc3.deposita(111.0);

        Conta cc4 = new ContaPoupanca(22, 22);
        Cliente clienteCC4 = new Cliente();
        clienteCC4.setNome("Ana");
        cc4.setTitular(clienteCC4);
        cc4.deposita(222.0);

        List<Conta> lista = new ArrayList<>();
        lista.add(cc1);
        lista.add(cc2);
        lista.add(cc3);
        lista.add(cc4);
        
        System.out.println("Lista sem ordenação");
        for (Conta conta : lista) {
			System.out.println(conta + ", " + conta.getTitular().getNome());
		}
        
        NumeroDaContaComparator numeroDaContaComparator = new NumeroDaContaComparator();
        lista.sort(numeroDaContaComparator);
        
        System.out.println("");
        System.out.println("Lista com ordenação por número da conta");
        for (Conta conta : lista) {
			System.out.println(conta + ", " + conta.getTitular().getNome());
        }
        
//        TitularDaContaComparator titularComparator = new TitularDaContaComparator();
        lista.sort(new TitularDaContaComparator());
        
        System.out.println("");
        System.out.println("Lista com ordenação por nome do titular da conta");
        for (Conta conta : lista) {
        	System.out.println(conta + ", " + conta.getTitular().getNome());
        }

        // ordenação antes do java 8
        Collections.sort(lista, new NumeroDaContaComparator());
        
        System.out.println("");
        System.out.println("Lista com ordenação por número da conta usando Collections");
        for (Conta conta : lista) {
        	System.out.println(conta + ", " + conta.getTitular().getNome());
        }
        
        Collections.reverse(lista);
        System.out.println("");
        System.out.println("Lista com ordenação reversa por número da conta usando Collections");
        for (Conta conta : lista) {
        	System.out.println(conta + ", " + conta.getTitular().getNome());
        }
        
        Collections.sort(lista);
        System.out.println("");
        System.out.println("Lista com ordenação por saldo da conta usando Collections na ordem natural");
        for (Conta conta : lista) {
        	System.out.println(conta + ", " + conta.getTitular().getNome());
        }
        
        // Má prática - NÃO USAR
        lista.sort(null);
        System.out.println("");
        System.out.println("Lista com ordenação por saldo da conta usando método sort de List");
        for (Conta conta : lista) {
        	System.out.println(conta + ", " + conta.getTitular().getNome());
        }
	}

}

class NumeroDaContaComparator implements Comparator<Conta> {

	@Override
	public int compare(Conta conta1, Conta conta2) {

//		opção 1
		return Integer.compare(conta1.getNumero(),conta2.getNumero());
		
//		opção 2
//		return conta1.getNumero() - conta2.getNumero();
		
//      opção 3
//		if (conta1.getNumero() > conta2.getNumero()) {
//			return 1;
//		}
//		
//		if (conta1.getNumero() < conta2.getNumero()) {
//			return -1;
//		}
//		
//		return 0;
	}
	
}

class TitularDaContaComparator implements Comparator<Conta> {

	@Override
	public int compare(Conta conta1, Conta conta2) {
		String nomeDaConta1 = conta1.getTitular().getNome();
		String nomeDaConta2 = conta2.getTitular().getNome();
		return nomeDaConta1.compareTo(nomeDaConta2);
	}
	
}
