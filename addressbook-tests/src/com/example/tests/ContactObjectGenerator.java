package com.example.tests;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 23.12.12
 */
public class ContactObjectGenerator extends BaseObjectGenerator {

    public static void main(String[] args) throws IOException {
        if(args.length < 3) {
            System.out.println("Please specify parameters: <amount of test date>,<file>,<format>");
            return;
        }

        int amount = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        String format = args[2];

        List<ContactObject> contacts = generateRandomContacts(amount);
        if(format.equals("csv")) {
            saveContactsToCsv(contacts,file);
        }
        else if(format.equals("xml")) {
            saveContactsToXml(contacts,file);
        }
        else {
            System.out.println("Unknown format: " + format);
        }
    }

    public static List<ContactObject> loadContactsFromXmlFile(File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("contact",ContactObject.class);
        return (List<ContactObject>) xStream.fromXML(file);
    }

    public static List<ContactObject> loadContactsFromCsvFile(String filename) throws IOException {
        List<ContactObject> list = new ArrayList<ContactObject>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            list.add(new ContactObject().withLastname(parts[0])
                    .withFirstname(parts[1])
                    .withPhone(parts[2]));
            line = br.readLine();
        }

        br.close();
        return list;
    }

    protected static void saveContactsToXml(List<ContactObject> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("contact",ContactObject.class);
        String xml = xStream.toXML(contacts);
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    protected static void saveContactsToCsv(List<ContactObject> contacts, File file) throws IOException{
        FileWriter writer = new FileWriter(file);
        for (ContactObject contact : contacts) {
            writer.write(contact.getLastname() + "," + contact.getFirstname() + "," + contact.getPhone() + ",!\n");
        }

        writer.close();
    }

    public static List<ContactObject> generateRandomContacts(int amount) {
        List<ContactObject> list = new ArrayList<ContactObject>();

        for(int i = 0; i < amount; i++) {
            ContactObject contactObject = new ContactObject()
                .withFirstname(generateRandomString())
                .withLastname(generateRandomString())
                .withPhone(generateRandomString());
            list.add(contactObject);
        }
        return list;
    }
}