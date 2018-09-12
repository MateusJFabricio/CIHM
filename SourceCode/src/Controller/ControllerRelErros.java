package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import DAO.LogFalhas;
import Views.ViewUnidades;

public class ControllerRelErros {

	public ArrayList<LogFalhas> buscarDados() {
		return Main.Main.mannBD.mannLogFalhas.buscarFalhas();
	}

	public void salvarCSV(ArrayList<LogFalhas> listProducao) {
		ViewUnidades viewUnidades = new ViewUnidades();
		if (!viewUnidades.path.isEmpty())
		{
			Date data = new Date(System.currentTimeMillis());  
			SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy"); 
			String sData = formatarDate.format(data);
			try {
				FileWriter fw = new FileWriter(viewUnidades.path + "RelatorioErros_" + sData + ".csv");
				fw.append("Log de Falhas - Equipamento ClaraMaq" + Character.toString('\n'));
				fw.append("Data:;" + sData + Character.toString('\n'));
				fw.append(Character.toString('\n'));
				fw.append( "Id Falha;DataHora;Desc Falha" + Character.toString('\n'));
				for (LogFalhas logFalhas : listProducao) {
					fw.append(
							String.valueOf(logFalhas.getId()) + ";" +
							logFalhas.getDataHora() + ";" +
							logFalhas.getFalha() + ";" +
							Character.toString('\n')
							);
				}
				fw.flush();
				fw.close();
				
				JOptionPane.showMessageDialog(null, "Arquivo gravado com sucesso");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Houve um erro: " + e.getMessage());
			}
		}
	}

}
