import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FaturamentoDiario {

    public static void main(String[] args) {

        try {
            File inputFile = new File("faturamento.xml"); // Substitua pelo nome do seu arquivo XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("dia");

            double menorFaturamento = Double.MAX_VALUE;
            double maiorFaturamento = Double.MIN_VALUE;
            double somaFaturamento = 0;
            int diasComFaturamento = 0;

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    double faturamento = Double.parseDouble(eElement.getElementsByTagName("valor").item(0).getTextContent());

                    if (faturamento > 0) {
                        menorFaturamento = Math.min(menorFaturamento, faturamento);
                        maiorFaturamento = Math.max(maiorFaturamento, faturamento);
                        somaFaturamento += faturamento;
                        diasComFaturamento++;
                    }
                }
            }

            double mediaMensal = somaFaturamento / diasComFaturamento;
            int diasAcimaDaMedia = 0;

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    double faturamento = Double.parseDouble(eElement.getElementsByTagName("valor").item(0).getTextContent());

                    if (faturamento > mediaMensal) {
                        diasAcimaDaMedia++;
                    }
                }
            }

            System.out.println("Menor faturamento diário: R$ " + menorFaturamento);
            System.out.println("Maior faturamento diário: R$ " + maiorFaturamento);
            System.out.println("Dias com faturamento acima da média mensal: " + diasAcimaDaMedia);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}