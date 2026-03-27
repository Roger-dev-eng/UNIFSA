package Classes;

import java.util.ArrayList;

public class Disciplina {
    private String nome;
    private Professor professor;
    private ArrayList<Aluno> alunos;

    public Disciplina(String nome, Professor professor) {
        if (professor == null) {
            throw new IllegalArgumentException("A disciplina deve ter um professor.");
        }

        this.nome = nome;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }   
    
    public String getNome() {
        return nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        if (professor == null) {
            throw new IllegalArgumentException("A disciplina deve ter um professor.");
        }
        this.professor = professor;
    }

    public void adicionarAluno(Aluno aluno) {

        if (aluno == null) {
            return;
        }

        if (!alunos.contains(aluno)) {
            alunos.add(aluno);
        } else {
            System.out.println("Aluno já está na disciplina.");
        }
    }

    public ArrayList<Aluno> getAlunos() {
        return new ArrayList<>(alunos); 
    }

}
