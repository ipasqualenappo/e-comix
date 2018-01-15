package GestioneProdotti;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<ComicsBean> comics;
	private List<GadgetBean> gadgets;

	public Cart() {
		comics = new ArrayList<ComicsBean>();
		gadgets = new ArrayList<GadgetBean>();
	}

	public void addComic(ComicsBean comic) {
		comics.add(comic);
	}

	public void addGadget(GadgetBean gadget) {
		gadgets.add(gadget);
	}

	public void deleteComic(ComicsBean comic) {
		for(ComicsBean com : comics) {
			if (com.getcode().equals(comic.getcode())) {
				comics.remove(com);
				break;
			}
		}
	}

	public void deleteGadget(GadgetBean gadget) {
		for(GadgetBean gadg : gadgets) {
			if(gadg.getcode().equals(gadget.getcode())) {
				gadgets.remove(gadg);
				break;
			}
		}
	}

	public List<GadgetBean> getgadget() {
		return  gadgets;
	}

	public List<ComicsBean> getcomic() {
		return  comics;
	}
}