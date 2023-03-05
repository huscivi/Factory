import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FactoryImpl implements Factory {
	private Holder first;
	private Holder last;
	private Integer size;	
	
	public FactoryImpl() {
		super();
		this.first =new Holder(null, null, null);
		this.last = new Holder(first, null, null);
		first.setNextHolder(last);
		last.setPreviousHolder(first);
		this.size = 0;
	}
	
	public Holder getFirst() {
		return first;
	}
	
	public int getSize()  {
		return size;
	}
	
	@Override
	public void addFirst(Product product) {
		Holder newHolder= new Holder(first, product,first.getNextHolder());
		Holder nextHolder= first.getNextHolder();
		nextHolder.setPreviousHolder(newHolder);
		first.setNextHolder(newHolder);
		size++;	
	}
	
	@Override
	public void addLast(Product product) {
		Holder newHolder= new Holder(last.getPreviousHolder(), product, last);
		last.getPreviousHolder().setNextHolder(newHolder);
		last.setPreviousHolder(newHolder);
		size++;
	}
	
	@Override
	public Product removeFirst() throws NoSuchElementException {				
	try {	
		if(size==0) {
			throw new NoSuchElementException();
		}
		Holder newHolder= first.getNextHolder().getNextHolder();
		Product deletingProduct= first.getNextHolder().getProduct();
		first.setNextHolder(newHolder);
		newHolder.setPreviousHolder(first);
		size--;
		return deletingProduct;
	}
	
	catch(NoSuchElementException e)  {
		throw new NoSuchElementException("Factory is empty.");
	}
	}
	
	@Override
	public Product removeLast() throws NoSuchElementException {	
		try {
		if(size==0) {
			throw new NoSuchElementException();
		}
		Holder newHolder= last.getPreviousHolder().getPreviousHolder();
		Product deletingProduct= last.getPreviousHolder().getProduct();
		last.setPreviousHolder(newHolder);
		newHolder.setNextHolder(last);
		size--;
		return deletingProduct;
		}
		catch (NoSuchElementException e) {
			throw new NoSuchElementException("Factory is empty.");
		}
	}
	
	@Override
	public Product find(int id) throws NoSuchElementException {
		Holder newHolder= first.getNextHolder();
		for(int i=0;i<size;i++) {		
			if(newHolder.getProduct().getId()== id)
				return newHolder.getProduct();
			else {
				Holder newHolder2= newHolder.getNextHolder();
				newHolder=newHolder2;
		}
		}
		throw new NoSuchElementException("Product not found.");		
	}
	
	@Override
	public Product update(int id, Integer value) throws NoSuchElementException {
		
		Holder newHolder= first.getNextHolder();
		for(int i=0;i<size;i++) {
			
			if(newHolder.getProduct().getId()== id) {
				int m= newHolder.getProduct().getId();
				int j= newHolder.getProduct().getValue();
				newHolder.getProduct().setValue(value);
				return new Product(m, j);
			}
			else {
				Holder newHolder2= newHolder.getNextHolder();
				newHolder=newHolder2;		
		}
	}
		throw new NoSuchElementException("Product not found.");
	}
	
	@Override
	public Product get(int index) throws IndexOutOfBoundsException {
		
		if(size<index) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		try {
		Holder newHolder= first.getNextHolder();
		for(int i=0; i<index;i++){
			newHolder= newHolder.getNextHolder();
		}
		return newHolder.getProduct();
	}
		catch (Exception e) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
	}
	
	@Override
	public void add(int index, Product product) throws IndexOutOfBoundsException {
		
		try {
		Holder newHolder= first.getNextHolder();
		for(int i=0; i<index;i++){
			Holder newHolder2 = newHolder.getNextHolder();
			newHolder= newHolder2;
		}
		Holder newHolder2 = new Holder(newHolder.getPreviousHolder(), product, newHolder);
		newHolder.getPreviousHolder().setNextHolder(newHolder2);
		newHolder.setPreviousHolder(newHolder2);
		size++;
	}
		catch (Exception e) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
	}
	
	@Override
	public Product removeIndex(int index) throws IndexOutOfBoundsException {
		
		try {
		if(size==0) {
			throw new NoSuchElementException();
		}
			Holder newHolder= first.getNextHolder();
		for(int i=0;i< index;i++) {
			Holder newHolder2 = newHolder.getNextHolder();
			newHolder= newHolder2;
		}
		newHolder.getPreviousHolder().setNextHolder(newHolder.getNextHolder());
		newHolder.getNextHolder().setPreviousHolder(newHolder.getPreviousHolder());
		size--;
		return newHolder.getProduct();
	}
		catch (Exception e) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
	}
		
	@Override
	public Product removeProduct(int value) throws NoSuchElementException {
		if(size==0) {
			throw new NoSuchElementException("Product not found.");
			
		}

		Holder newHolder= first.getNextHolder();
		for(int i=0;i<size;i++) {
			if(newHolder.getProduct().getValue()== value) {
				newHolder.getPreviousHolder().setNextHolder(newHolder.getNextHolder());
				newHolder.getNextHolder().setPreviousHolder(newHolder.getPreviousHolder());
				size--;	
				return newHolder.getProduct();
			}
			else {
				newHolder=newHolder.getNextHolder();
			}		
		}
		throw new NoSuchElementException("Product not found.");
			

	}
	
	
	@Override
	public int filterDuplicates() {
		int firstSize= size;
		FactoryImpl factory2= new FactoryImpl();
		Holder newHolder= first.getNextHolder();
		ArrayList <Integer> valueOfProducts = new ArrayList<>();
		for(int i=0;i<size;i++) {
			if (!valueOfProducts.contains(newHolder.getProduct().getValue())) {
				valueOfProducts.add(newHolder.getProduct().getValue());
				factory2.addLast(newHolder.getProduct());
			}
			newHolder=newHolder.getNextHolder();
		}
		first.setNextHolder(factory2.first.getNextHolder());
		factory2.first.getNextHolder().setPreviousHolder(first);
		last.setPreviousHolder(factory2.last.getPreviousHolder());
		factory2.last.getPreviousHolder().setNextHolder(last);
		size= valueOfProducts.size();
		return firstSize-valueOfProducts.size();	
	}
	
	@Override
	public void reverse() {
		
		FactoryImpl factory2= new FactoryImpl();
		Holder newHolder= last.getPreviousHolder();
		for(int i=0;i<size;i++) {
			factory2.addLast(newHolder.getProduct());
			newHolder=newHolder.getPreviousHolder();
		}
		
		first.setNextHolder(factory2.first.getNextHolder());
		factory2.first.getNextHolder().setPreviousHolder(first);
		last.setPreviousHolder(factory2.last.getPreviousHolder());
		factory2.last.getPreviousHolder().setNextHolder(last);
	}
	
	public void print() {
		
	}
	

	
}
