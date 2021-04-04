//Fabio Tott Mormillo RA: 19.00176-2
// Conrado Pupo Azzalin RA:19.01490-2

import java.util.Random;
import java.util.Scanner;

class User {
  private String Name;
  private String Pass;
  private String Email;

  public User(String name, String pass, String email) {
    Name = name;
    Pass = pass;
    Email = email;
  }

  public String getName(){
    return Name;
  }

  public void setName(String name){
    Name = name;
  }

  public String getPass(){
    return Pass;
  }

  public void setPass(String pass){
    Pass = pass;
  }

  public String getEmail(){
    return Email;
  }

  public void setEmail(String email){
    Email = email;
  }
}



class Conta {
  private int IdConta;
  private float Saldo;
  private User User;

  public Conta(int idConta, float saldo, User user){
    IdConta = idConta;
    Saldo = saldo;
    User = user;
  }

  public int getIdConta(){
    return IdConta;
  }

  public void setIdConta(int idConta){
    IdConta = idConta;
  }

  public float getSaldo(){
    return Saldo;
  }

  public void setSaldo(float saldo){
    Saldo = saldo;
  }

  public User getUser(){
    return User;
  }
}


class Transacao {
  public String getTransacaoString(int id, String name, float valor){
    Random r = new Random();
    int random = r.nextInt((9999 - 1000) + 1) + 1000;

    return id + ";" + name + ";" + valor + ";" + random;
  }

  public String realizarTransferencia(Conta pagador, Conta recebedor, String Ts){
    String[] dados = Ts.split(";"); 
    
    if(recebedor.getIdConta() != Integer.parseInt(dados[0]))
      return "ID da conta incorreta";

    if(recebedor.getUser().getName() == dados[1])
      return "Nome do recebedor incorreto";

    if(pagador.getSaldo() < Float.parseFloat(dados[2]))
      return "Saldo insuficiente";

    pagador.setSaldo(pagador.getSaldo() - Float.parseFloat(dados[2]));
    recebedor.setSaldo(recebedor.getSaldo() + Float.parseFloat(dados[2]));

    return "Transferencia realizada com sucesso!";
  }
}


class Main {  
  public static void main(String args[]) { 
    Scanner ler = new Scanner(System.in);
    System.out.println("Informe o nome do usuário 1:");
    String name1 = ler.nextLine();
    System.out.println("Informe o nome do usuário 2:");
    String name2 = ler.nextLine();
    System.out.println("Informe o nome do usuário 3:");
    String name3 = ler.nextLine();

    User u1 = new User(name1, "123", "email@email");
    User u2 = new User(name2, "123", "email@email");
    User u3 = new User(name3, "123", "email@email");

    Conta acc1 = new Conta(1, 1000, u1);
    Conta acc2 = new Conta(2, 250, u2);
    Conta acc3 = new Conta(3, 3000, u3);

    Transacao t = new Transacao();

    System.out.println("Estado Inicial:");
    System.out.printf("Nome de usuário: %s - Saldo: %.2f", name1, acc1.getSaldo());
    System.out.printf("\nNome de usuário: %s - Saldo: %.2f", name2, acc2.getSaldo());
    System.out.printf("\nNome de usuário: %s - Saldo: %.2f", name3, acc3.getSaldo());

    String st = t.getTransacaoString(1, name1, 250);
    String trans = t.realizarTransferencia(acc2, acc1, st);
    String trans1 = t.realizarTransferencia(acc3, acc1, st);

    String st1 = t.getTransacaoString(2, name2, 1000);
    String trans2 = t.realizarTransferencia(acc3, acc2, st1);

    System.out.println("\nEstado Final:");
    System.out.printf("Nome de usuário: %s - Saldo: %.2f", name1, acc1.getSaldo());
    System.out.printf("\nNome de usuário: %s - Saldo: %.2f", name2, acc2.getSaldo());
    System.out.printf("\nNome de usuário: %s - Saldo: %.2f", name3, acc3.getSaldo());
  } 
}
