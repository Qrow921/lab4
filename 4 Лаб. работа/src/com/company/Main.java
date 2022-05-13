package com.company;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;




public class Main
{
    private static Scanner scan = new Scanner(System.in);
    private static int objectsCount;
    private static ArrayList<Library> Texts = new ArrayList<>();
    private static ArrayList<Library> SameResult = new ArrayList<>();
    private static ArrayList<CollectionOfArticles> Collections = new ArrayList<>();
    private static ArrayList<SeriesOfEssays> Series = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        OutputStream A = new ByteArrayOutputStream();
        FileOutputStream file = new FileOutputStream("C:\\Users\\Kuja\\Desktop\\file.txt");
        Writer B = new CharArrayWriter();
        //byte[] arr = A;
        FillArrayList(1);
        InputStream in = null;
        MyStaticMethods.serializeLibrary(Texts.get(0),file);
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Kuja\\Desktop\\file.txt");
        Texts.add(MyStaticMethods.deserializeLibrary(inputStream));

        System.out.println(Texts.get(0));
        System.out.println(Texts.get(1));

    }

    public static void SplitArrayByType(ArrayList arr)
    {
        for(int i = 0; i< arr.size(); i++)
        {
            if (arr.get(i) instanceof CollectionOfArticles)
                Collections.add((CollectionOfArticles) arr.get(i));
            if (arr.get(i) instanceof SeriesOfEssays)
                Series.add((SeriesOfEssays)arr.get(i));
        }
    }
    public static void ArrayOfSameResults()
    {
        for (int start = 0; start < Texts.size()-1; start++) {
            for (int index = start + 1; index < Texts.size(); index++)
            {
                {
                    if (Texts.get(start).QuotedPages() == Texts.get(index).QuotedPages())
                    {
                        SameResult.add(Texts.get(start));
                        SameResult.add(Texts.get(index));
                    }
                }
            }
        }
    }
    public static void OutputArray(ArrayList arr)
    {
        for(int i = 0; i< arr.size(); i++)
        {
            System.out.println(arr.get(i));
        }
    }
    public static void FillArrayList(int count)
    {
        int i;
        for (i=0;i<count;i++)
        {
            GenerateNewObject();
        }
    }
    public static void SetObjectsCount(int count)
    {
        if (!(count < 0))
        {
            objectsCount = count;
        }
        else
        {
            System.out.println("������� ������������ ��������! ���������� ��������� �� ����� ��������� ���� ��� ������ ����");
        }
    }
    public static int GetObjectsCount()
    {
        return objectsCount;
    }
    private static void GenerateNewObject()
    {
        String inputType;
        String inputName;
        int inputCount = 0;
        int inputUnquotedPagesCount;

        System.out.println("����� ��������� �������? �����(����) ��� ���������(������)? ������� ����� ����� ��� ����� ���������: ");
            inputType = scan.nextLine();
        System.out.println("������� ��� �����/���������: ");
            inputName = scan.nextLine();
        System.out.println("������� ���-�� ���� ��� ������: ");
            inputCount = Integer.parseInt(scan.nextLine());
        System.out.println("������� �� ������� ���������� (������� ���������� ��� ���������):");
        inputUnquotedPagesCount = Integer.parseInt(scan.nextLine());
        int[] temp = new int[inputCount];

        //���������� ���������� ������� �� 10 �� 130
        int begin=10;
        int end = 130;
        for (int i =0;i<inputCount;i++)
        {
            temp[i] = begin + (int)(Math.random() * 130);
        }

        //������ ��������� ������ ������������ � ������� ��� � ������ ����������
        if (inputType.equals("�����") || inputType.equals("�����") || inputType.equals("�����"))
        {
            Texts.add(new SeriesOfEssays(temp,inputName,inputUnquotedPagesCount));
        }
        if (inputType.equals("���������") || inputType.equals("���������") || inputType.equals("���������"))
        {
            Texts.add(new CollectionOfArticles(temp,inputName,inputUnquotedPagesCount));
        }
    }
}

class ValidationException extends Exception
{
    ValidationException(String errorMessage,Throwable err)
    {
        super(errorMessage, err);
    }
}

class OutOfBounds extends RuntimeException
{
    OutOfBounds(String errorMessage, Throwable err)
    {
        super(errorMessage,err);
    }
}

interface Library
{
    String GetName();
    boolean SameType(Object obj);
    void SetName(String name) throws ValidationException;
    void SetPagesArray(int[] array);
    void SetPageCount(int pageNumber, int value);
    void SetCountOfUnquotedPages(int count);
    void Output(OutputStream out) throws IOException;
    void Write(Writer out) throws IOException;
    int[] GetPagesArray();
    int GetPageCount (int pageNumber);
    int QuotedPages();
    int GetCountOfUnquotedPages();

}