package package_Data;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;

public class ImgData {
	private Pimgs[] dt;
	private int id; // current - next
	private Random rgen;

	public ImgData(String dirName) {
		rgen = new Random();
		loadFile(new File(dirName));
	}

	public ImgData(Collection<Pimgs> data) {
		dt = new Pimgs[data.size()];
		Iterator<Pimgs> it = data.iterator();
		for (int i = 0; i < dt.length; i++)
			dt[i] = it.next();
		shuffle();
	}

	/** Retrieve next entry. Auto shuffle if isDone. */
	public Pimgs next() {
		if (isDone())
			shuffle();
		return dt[id++];
	}

	public Pimgs current() {
		if (isDone())
			return null;
		return dt[id];
	}

	public int getIndex() {
		if (isDone())
			return -1;
		return id;
	}

	public void loadFile(File dir) {
		if (!dir.isDirectory()) {
			System.out.print("Wrong directory");
			System.exit(-1);
		}

		String[] imgList = dir.list();
		int e = imgList.length;
		for (int i = 0; i < e; i++)
			if ((!imgList[i].endsWith(".jpg")) && (!imgList[i].endsWith(".png"))) {
				imgList[i] = imgList[--e];
				i--;
			}
		if (e != imgList.length) {
			String[] tmp = new String[e];
			for (int i = 0; i < e; i++)
				tmp[i] = imgList[i];
			imgList = tmp;
		}
		if (imgList.length == 0) {
			System.out.print("Brak obrazow");
			System.exit(-1);
		}

		dt = new Pimgs[e];
		for (int i = 0; i < dt.length; i++)
			dt[i] = new Pimgs(new ImageIcon(dir.getParent() + "/" + dir.getName() + "/" + imgList[i]).getImage(),
					imgList[i].substring(0, imgList[i].length() - 4));

		shuffle();
	}

	public void shuffle() {
		for (int i = (int) Math.pow(rgen.nextInt(dt.length), 2); i > 0; i--) {
			int a = rgen.nextInt(dt.length);
			int b = rgen.nextInt(dt.length);
			Pimgs tmp = dt[a];
			dt[a] = dt[b];
			dt[b] = tmp;
		}
		id = 0;
	}

	public boolean isDone() {
		return id == dt.length;
	}

	public int size() {
		return dt.length;
	}

	@Override
	public String toString() {
		return "ImgData " + Arrays.toString(dt);
	}

	public void prt() {
		System.out.println(this);
	}

}
