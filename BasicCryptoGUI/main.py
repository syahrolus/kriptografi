from tkinter import *
from sympy.crypto.crypto import encipher_hill, decipher_hill
from Crypto.Util.number import bytes_to_long, long_to_bytes
from sympy import Matrix
from Crypto.Util.number import GCD
from secretpy import Playfair, CryptMachine
from secretpy.cmdecorators import UpperCase
import string, random, enchant

kript = [
	"Shitt Cipher",					# Encrypt, Decrypt, Bruteforce
	"Subtitution Cipher",			# Encrypt, Decrypt, GenerateAlp
	"Affine Cipher",				# Encrypt, Decrypt, Bruteforce, Filter bruteforce
	"Vigenere Cipher",				# Encrypt, Decrypt
	"Extended Vigenere Cipher",		# Encrypt, Decrypt, Hex
	"Hill Chipher",					# Encrypt, Decrypt (masih buruk)
	"Playfair Cipher", 				# Encrypt, Decrypt (masih buruk)
]



def shift(): # DONE
	def shift_encrypt():
		e3.delete(0, "end")
		pt = e1.get()
		nm = int(e2.get())
		ct = ""
		for i in pt:
			if i in string.ascii_lowercase:
				ct += string.ascii_lowercase[((ord(i)-97)+nm)%26]
			elif i in string.ascii_uppercase:
				ct += string.ascii_uppercase[((ord(i)-65)+nm)%26]
			else:
				ct += i
		e3.insert(0, ct)

	def shift_decrypt():
		listbox.delete(0, "end")
		pt = e4.get()
		nm = int(e5.get())
		ct = ""
		for i in pt:
			if i in string.ascii_lowercase:
				ct += string.ascii_lowercase[((ord(i)-97)-nm)%26]
			elif i in string.ascii_uppercase:
				ct += string.ascii_uppercase[((ord(i)-65)-nm)%26]
			else:
				ct += i
		listbox.insert(1, ct)

	def shift_decrypt_brute():
		listbox.delete(0, "end")
		pt = e4.get()
		ct = ""
		for y in range(26):
			ct += str(y)+" : "
			for i in pt:
				if i in string.ascii_lowercase:
					ct += string.ascii_lowercase[((ord(i)-97)-y)%26]
				elif i in string.ascii_uppercase:
					ct += string.ascii_uppercase[((ord(i)-65)-y)%26]
				else:
					ct += i
			ct += "\n"
		res = ct.split("\n")
		for i in range(len(res)):
			listbox.insert(i+1, res[i])


	newWindow = Toplevel(wd)
	newWindow.title("Shitt Cipher")
	# enkripsi
	label1 = Label(newWindow, text ='plain text')               
	label1.grid(row = 0, column = 0)
	e1 = Entry(newWindow)
	e1.grid(row = 0, column = 1)
	label2 = Label(newWindow, text ='shift number')
	label2.grid(row = 1, column = 0)
	e2 = Entry(newWindow)
	e2.grid(row = 1, column = 1)
	ent = Button(newWindow, text = "encrypt", command = shift_encrypt)
	ent.grid(row = 2, column = 1)
	e3 = Entry(newWindow)
	e3.grid(row = 3, column = 1)

	# dekripsi
	label3 = Label(newWindow, text ='cipher text')               
	label3.grid(row = 0, column = 2)
	e4 = Entry(newWindow)
	e4.grid(row = 0, column = 3, padx=30)
	label4 = Label(newWindow, text ='shift number')
	label4.grid(row = 1, column = 2)
	e5 = Entry(newWindow)
	e5.grid(row = 1, column = 3)
	ent = Button(newWindow, text = "decrypt", command = shift_decrypt)
	ent.grid(row = 2, column = 3)
	ent = Button(newWindow, text = "brute force", command = shift_decrypt_brute)
	ent.grid(row = 3, column = 3)
	listbox = Listbox(newWindow, height = 10, width = 30)
	listbox.grid(row = 4, column = 3)


def substitution():
	def generate():
		e2.delete(0, "end")
		a = string.ascii_lowercase
		ran = ""
		while a != "":
			tmp = random.choice(a)
			ran += tmp
			a = a.replace(tmp, "")
		e2.insert(0, ran)
	def alp_encrypt():
		e3.delete(0, "end")
		pt = e1.get()
		nm = e2.get()
		ct = ""

		for i in pt:
			if i in string.ascii_lowercase:
				ct += nm[string.ascii_lowercase.index(i)]
			elif i in string.ascii_uppercase:
				ct += nm[string.ascii_uppercase.index(i)].upper()
			else:
				ct += i
		e3.insert(0, ct)

	def alp_decrypt():
		listbox.delete(0, "end")
		pt = e4.get()
		nm = e5.get()
		ct = ""
		for i in pt:
			if i in string.ascii_lowercase:
				ct += string.ascii_lowercase[nm.index(i)]
			elif i in string.ascii_uppercase:
				ct += string.ascii_uppercase[nm.index(i.lower())]
			else:
				ct += i
		listbox.insert(1, ct)

	newWindow = Toplevel(wd)
	newWindow.title("Subtitution Cipher")
	# enkripsi
	label1 = Label(newWindow, text ='plain text')               
	label1.grid(row = 0, column = 0)
	e1 = Entry(newWindow)
	e1.grid(row = 0, column = 1)
	label2 = Label(newWindow, text ='alp cipher')
	label2.grid(row = 1, column = 0)
	e2 = Entry(newWindow)
	e2.grid(row = 1, column = 1)
	gen = Button(newWindow, text = "generate", command = generate)
	gen.grid(row = 2, column = 1)
	ent = Button(newWindow, text = "encrypt", command = alp_encrypt)
	ent.grid(row = 3, column = 1)
	e3 = Entry(newWindow)
	e3.grid(row = 4, column = 1)

	# dekripsi
	label3 = Label(newWindow, text ='cipher text')               
	label3.grid(row = 0, column = 2)
	e4 = Entry(newWindow)
	e4.grid(row = 0, column = 3, padx=30)
	label4 = Label(newWindow, text ='alp cipher')
	label4.grid(row = 1, column = 2)
	e5 = Entry(newWindow)
	e5.grid(row = 1, column = 3)
	ent = Button(newWindow, text = "decrypt", command = alp_decrypt)
	ent.grid(row = 2, column = 3)
	# ent = Button(newWindow, text = "brute force", command = None)
	# ent.grid(row = 3, column = 3)
	label5 = Label(newWindow, text ='')
	label5.grid(row = 3, column = 2)
	label6 = Label(newWindow, text ='')
	label6.grid(row = 4, column = 2)
	listbox = Listbox(newWindow, height = 10, width = 30)
	listbox.grid(row = 5, column = 3)

def affine():
	def affine_encrypt():
		e4.delete(0, "end")
		pt = e1.get()
		a = int(e2.get())
		b = int(e3.get())
		ct = ""
		for t in pt:
			if t in string.ascii_lowercase:
				ct += chr((( a*(ord(t) - ord('a')) + b ) % 26) + ord('a'))
			elif t in string.ascii_uppercase:
				ct += chr((( a*(ord(t) - ord('A')) + b ) % 26) + ord('A'))
			else:
				ct += t

		e4.insert(1,ct)

	def egcd(a, b):
	    x,y, u,v = 0,1, 1,0
	    while a != 0:
	        q, r = b//a, b%a
	        m, n = x-u*q, y-v*q
	        b,a, x,y, u,v = a,r, u,v, m,n
	    gcd = b
	    return gcd, x, y

	def modinv(a, m):
	    gcd, x, y = egcd(a, m)
	    if gcd != 1:
	        return None # modular inverse does not exist
	    else:
	        return x % m

	def affine_decrypt():
		listbox.delete(0, "end")
		listbox2.delete(0, "end")
		ct = e5.get()
		a = int(e6.get())
		b = int(e7.get())
		pt = ""
		# pt = "".join([ chr((( modinv(a, 26)*(ord(c) - ord('A') - b)) % 26) + ord('A')) for c in ct ])
		for t in ct:
			if t in string.ascii_lowercase:
				pt += chr((( modinv(a, 26)*(ord(t) - ord('a') - b)) % 26) + ord('a'))
			elif t in string.ascii_uppercase:
				pt += chr((( modinv(a, 26)*(ord(t) - ord('A') - b)) % 26) + ord('A'))
			else:
				pt += t
		listbox.insert(1, pt)

	def is_coprime(x, y):
		return GCD(x, y) == 1
	def affine_brute():
		listbox.delete(0, "end")
		listbox2.delete(0, "end")
		ct = e5.get()
		pt = ""
		x = int(e6.get())
		y = int(e7.get())
		for a in range(x+1):
			if is_coprime(a, 26):
				for b in range(y+1):
					tmp = ""
					for t in ct:
						if t in string.ascii_lowercase:
							tmp += chr((( modinv(a, 26)*(ord(t) - ord('a') - b)) % 26) + ord('a'))
						elif t in string.ascii_uppercase:
							tmp += chr((( modinv(a, 26)*(ord(t) - ord('A') - b)) % 26) + ord('A'))
						else:
							tmp += t
					pt += tmp
					pt += "\n"
		res = pt.split("\n")
		d = enchant.Dict("en_US")
		filt = []
		for i in range(len(res)-1):
			listbox.insert(i+1, res[i])
			tmp = res[i].split(" ")
			# print(tmp,end=', ')
			for y in range(len(tmp)):
				# print(d.check(tmp[y].lower()))
				if d.check(tmp[y].lower()):
					filt.append(res[i])
					break
		for i in range(len(filt)):
			listbox2.insert(i+1, filt[i])
	newWindow = Toplevel(wd)
	newWindow.title("Affine Cipher")

	# enkripsi
	label1 = Label(newWindow, text ='plain text')               
	label1.grid(row = 0, column = 0)
	e1 = Entry(newWindow)
	e1.grid(row = 0, column = 1)
	label2 = Label(newWindow, text ='slope/A')
	label2.grid(row = 1, column = 0)
	e2 = Entry(newWindow)
	e2.grid(row = 1, column = 1)
	label3 = Label(newWindow, text ='intercept/B')
	label3.grid(row = 2, column = 0)
	e3 = Entry(newWindow)
	e3.grid(row = 2, column = 1)
	ent = Button(newWindow, text = "encrypt", command = affine_encrypt)
	ent.grid(row = 3, column = 1)
	e4 = Entry(newWindow)
	e4.grid(row = 4, column = 1)

	# dekripsi
	label3 = Label(newWindow, text ='cipher text')               
	label3.grid(row = 0, column = 2)
	e5 = Entry(newWindow)
	e5.grid(row = 0, column = 3, padx=30)
	label4 = Label(newWindow, text ='slope/A')
	label4.grid(row = 1, column = 2)
	e6 = Entry(newWindow)
	e6.grid(row = 1, column = 3)
	label4 = Label(newWindow, text ='intercept/B')
	label4.grid(row = 2, column = 2)
	e7 = Entry(newWindow)
	e7.grid(row = 2, column = 3)
	ent = Button(newWindow, text = "decrypt", command = affine_decrypt)
	ent.grid(row = 3, column = 3)
	ent = Button(newWindow, text = "brute force", command = affine_brute)
	ent.grid(row = 4, column = 3)
	listbox = Listbox(newWindow, height = 10, width = 30)
	listbox.grid(row = 5, column = 3)
	listbox2 = Listbox(newWindow, height = 10, width = 30)
	listbox2.grid(row = 6, column = 3)



def vigenere():
	def vigenere_encrypt():
		e3.delete(0, "end")
		pt = e1.get()
		ptn = ""
		for i in pt.lower():
			if i not in (string.punctuation+" "+string.digits):
				ptn += i
		key = e2.get().lower()
		keyn = ''.join([key[i%len(key)] for i in range(len(ptn))])
		ct = ""
		alp = string.ascii_lowercase
		for i in range(len(ptn)):
			# if ptn[i] in string.ascii_lowercase:
			ct += (alp[alp.index(keyn[i]):]+alp[:alp.index(keyn[i])])[alp.index(ptn[i].lower())]
			# else:
				# ct += ptn[i]
		ctn = ""
		y = 0
		for i in range(len(pt)):
			if pt[i] in string.ascii_lowercase:
				ctn += ct[y]
				y += 1
			elif pt[i] in string.ascii_uppercase:
				ctn += ct[y].upper()
				y += 1
			else:
				ctn += pt[i]

		e3.insert(0, ctn)

	def vigenere_decrypt():
		listbox.delete(0, "end")
		pt = e4.get()
		ptn = ""
		for i in pt.lower():
			if i not in (string.punctuation+" "+string.digits):
				ptn += i
		key = e5.get().lower()
		keyn = ''.join([key[i%len(key)] for i in range(len(ptn))])
		ct = ""
		alp = string.ascii_lowercase
		for i in range(len(ptn)):
			if ptn[i] in string.ascii_lowercase:
				ct += alp[(alp[alp.index(keyn[i]):]+alp[:alp.index(keyn[i])]).index(ptn[i].lower())]
			else:
				ct += ptn[i]
		ctn = ""
		y = 0
		for i in range(len(pt)):
			if pt[i] in string.ascii_lowercase:
				ctn += ct[y]
				y += 1
			elif pt[i] in string.ascii_uppercase:
				ctn += ct[y].upper()
				y += 1
			else:
				ctn += pt[i]
		listbox.insert(1, ctn)


	newWindow = Toplevel(wd)
	newWindow.title("Vigenere Cipher")
	# enkripsi
	label1 = Label(newWindow, text ='plain text')               
	label1.grid(row = 0, column = 0)
	e1 = Entry(newWindow)
	e1.grid(row = 0, column = 1)
	label2 = Label(newWindow, text ='key')
	label2.grid(row = 1, column = 0)
	e2 = Entry(newWindow)
	e2.grid(row = 1, column = 1)
	ent = Button(newWindow, text = "encrypt", command = vigenere_encrypt)
	ent.grid(row = 2, column = 1)
	e3 = Entry(newWindow)
	e3.grid(row = 3, column = 1)

	# dekripsi
	label3 = Label(newWindow, text ='cipher text')               
	label3.grid(row = 0, column = 2)
	e4 = Entry(newWindow)
	e4.grid(row = 0, column = 3, padx=30)
	label4 = Label(newWindow, text ='key')
	label4.grid(row = 1, column = 2)
	e5 = Entry(newWindow)
	e5.grid(row = 1, column = 3)
	ent = Button(newWindow, text = "decrypt", command = vigenere_decrypt)
	ent.grid(row = 2, column = 3)
	label5 = Label(newWindow, text ='')
	label5.grid(row = 3, column = 2)
	listbox = Listbox(newWindow, height = 10, width = 30)
	listbox.grid(row = 4, column = 3)

def ev():
	def ev_encrypt():
		e3.delete(0, "end")
		e6.delete(0, "end")
		pt = e1.get()
		key = e2.get().lower()
		keyn = ''.join([key[i%len(key)] for i in range(len(pt))])
		alp = ''
		for i in range(256):
			alp += chr(i)
		ct = ""
		for i in range(len(pt)):
			ct += (alp[alp.index(keyn[i]):]+alp[:alp.index(keyn[i])])[alp.index(pt[i])]
		e3.insert(0, ct)
		ct2 = ""
		for i in ct:
			ct2 += hex(ord(i))[2:]
		e6.insert(0, ct2)

	def ev_decrypt():
		e7.delete(0, "end")
		p1 = e4.get()
		p2 = e8.get()
		if p1 == "":
			tmp = ""
			for i in range(0,len(p2),2):
				tmp += chr(int(p2[i:i+2],16))
			pt = tmp
		elif p2 == "":
			pt = p1
		key = e5.get().lower()
		keyn = ''.join([key[i%len(key)] for i in range(len(pt))])
		alp = ''
		for i in range(256):
			alp += chr(i)
		ct = ""
		for i in range(len(pt)):
			ct += alp[(alp[alp.index(keyn[i]):]+alp[:alp.index(keyn[i])]).index(pt[i])]
		e7.insert(0, ct)


	newWindow = Toplevel(wd)
	newWindow.title("Extended Vigenere Cipher")
	# enkripsi
	label1 = Label(newWindow, text ='plain text')               
	label1.grid(row = 0, column = 0)
	e1 = Entry(newWindow)
	e1.grid(row = 0, column = 1)
	label2 = Label(newWindow, text ='key')
	label2.grid(row = 1, column = 0)
	e2 = Entry(newWindow)
	e2.grid(row = 1, column = 1)
	ent = Button(newWindow, text = "encrypt", command = ev_encrypt)
	ent.grid(row = 3, column = 1)
	e3 = Entry(newWindow)
	e3.grid(row = 4, column = 1)
	e6 = Entry(newWindow)
	e6.grid(row = 5, column = 1)

	# dekripsi
	label3 = Label(newWindow, text ='cipher text')               
	label3.grid(row = 0, column = 2)
	e4 = Entry(newWindow)
	e4.grid(row = 0, column = 3, padx=30)
	label4 = Label(newWindow, text ='cipher text(hex)')
	label4.grid(row = 1, column = 2)
	e8 = Entry(newWindow)
	e8.grid(row = 1, column = 3, padx=30)
	label4 = Label(newWindow, text ='key')
	label4.grid(row = 2, column = 2)
	e5 = Entry(newWindow)
	e5.grid(row = 2, column = 3)
	ent = Button(newWindow, text = "decrypt", command = ev_decrypt)
	ent.grid(row = 3, column = 3)
	e7 = Entry(newWindow)
	e7.grid(row = 4, column = 3)

def hill():
	def hill_encrypt():
		e4.delete(0, "end")
		pt = e1.get()
		sm = int(e2.get())
		insi = e3.get()
		a = [[None for y in range(sm)] for x in range(sm)]
		c = 0
		for i in range(sm):
			for y in range(sm):
				if insi[c] in string.ascii_lowercase:
					a[i][y] = (ord(insi[c])-ord('a')+1)
				elif insi[c] in string.ascii_uppercase:
					a[i][y] = (ord(insi[c])-ord('A')+1)
				elif insi[c] in string.digits:
					a[i][y] = (ord(insi[c])-ord('0')+1)
				c+=1
		key = Matrix(a)
		tx = encipher_hill(pt, key)
		e4.insert(0, tx)
	def hill_decrypt():
		listbox.delete(0, "end")
		ct = e5.get()
		sm = int(e6.get())
		insi = e7.get()
		a = [[None for y in range(sm)] for x in range(sm)]
		c = 0
		for i in range(sm):
			for y in range(sm):
				if insi[c] in string.ascii_lowercase:
					a[i][y] = (ord(insi[c])-ord('a')+1)
				elif insi[c] in string.ascii_uppercase:
					a[i][y] = (ord(insi[c])-ord('A')+1)
				elif insi[c] in string.digits:
					a[i][y] = (ord(insi[c])-ord('0')+1)
				c+=1
		key = Matrix(a)
		tx = decipher_hill(ct, key)
		listbox.insert(0, tx)

	newWindow = Toplevel(wd)
	newWindow.title("Hill Cipher")
	# enkripsi
	label1 = Label(newWindow, text ='plain text')               
	label1.grid(row = 0, column = 0)
	e1 = Entry(newWindow)
	e1.grid(row = 0, column = 1)
	label2 = Label(newWindow, text ='square matrix')
	label2.grid(row = 1, column = 0)
	e2 = Entry(newWindow)
	e2.grid(row = 1, column = 1)
	label3 = Label(newWindow, text ='key')
	label3.grid(row = 2, column = 0)
	e3 = Entry(newWindow)
	e3.grid(row = 2, column = 1)
	label4 = Label(newWindow, text ='abdefghij')
	label4.grid(row = 3, column = 1)
	label5 = Label(newWindow, text ='[[a,b,d],[e,f,g],[h,i,j]]')
	label5.grid(row = 4, column = 1)
	ent = Button(newWindow, text = "encrypt", command = hill_encrypt)
	ent.grid(row = 5, column = 1)
	e4 = Entry(newWindow)
	e4.grid(row = 6, column = 1)

	# dekripsi
	label6 = Label(newWindow, text ='cipher text')               
	label6.grid(row = 0, column = 2)
	e5 = Entry(newWindow)
	e5.grid(row = 0, column = 3, padx=30)
	label7 = Label(newWindow, text ='square matrix')
	label7.grid(row = 1, column = 2)
	e6 = Entry(newWindow)
	e6.grid(row = 1, column = 3)
	label4 = Label(newWindow, text ='key')
	label4.grid(row = 2, column = 2)
	e7 = Entry(newWindow)
	e7.grid(row = 2, column = 3)
	ent = Button(newWindow, text = "decrypt", command = hill_decrypt)
	ent.grid(row = 3, column = 3)
	ent = Label(newWindow, text = "", command = None)
	ent.grid(row = 4, column = 3)
	listbox = Listbox(newWindow, height = 10, width = 30)
	listbox.grid(row = 7, column = 3)


def playfair():
	def playfair_encrypt():
		e4.delete(0, "end")
		pt = e1.get()
		sm = int(e2.get())
		insi = e3.get()
		cm = UpperCase(CryptMachine(Playfair()))
		alphabet = []
		for i in range(sm**2):
			alphabet.append(insi[i])
		cm.set_alphabet(alphabet)
		c = cm.encrypt(pt)
		e4.insert(0, c)

	def playfair_decrypt():
		e8.delete(0, "end")
		c = e5.get()
		sm = int(e6.get())
		insi = e7.get()
		cm = UpperCase(CryptMachine(Playfair()))
		alphabet = []
		for i in range(sm**2):
			alphabet.append(insi[i])
		cm.set_alphabet(alphabet)
		pt = cm.decrypt(c)
		e8.insert(0, pt)

	newWindow = Toplevel(wd)
	newWindow.title("Hill Cipher")
	# enkripsi
	label1 = Label(newWindow, text ='plain text')               
	label1.grid(row = 0, column = 0)
	e1 = Entry(newWindow)
	e1.grid(row = 0, column = 1)
	label2 = Label(newWindow, text ='square matrix')
	label2.grid(row = 1, column = 0)
	e2 = Entry(newWindow)
	e2.grid(row = 1, column = 1)
	label3 = Label(newWindow, text ='key')
	label3.grid(row = 2, column = 0)
	e3 = Entry(newWindow)
	e3.grid(row = 2, column = 1)
	label4 = Label(newWindow, text ='abcdefghijklmnopqrstuvwxy')
	label4.grid(row = 3, column = 1)
	label5 = Label(newWindow, text ='[[a,b,c,d,e],')
	label5.grid(row = 4, column = 1)
	label6 = Label(newWindow, text ='[f,g,h,i,j],')
	label6.grid(row = 5, column = 1)
	label7 = Label(newWindow, text ='[k,l,m,n,o]')
	label7.grid(row = 6, column = 1)
	label8 = Label(newWindow, text ='[p,q,r,s,t]')
	label8.grid(row = 7, column = 1)
	label9 = Label(newWindow, text ='[u,v,w,x,y]]')
	label9.grid(row = 8, column = 1)
	ent = Button(newWindow, text = "encrypt", command = playfair_encrypt)
	ent.grid(row = 9, column = 1)
	e4 = Entry(newWindow)
	e4.grid(row = 10, column = 1)

	# dekripsi
	label6 = Label(newWindow, text ='cipher text')               
	label6.grid(row = 0, column = 2)
	e5 = Entry(newWindow)
	e5.grid(row = 0, column = 3, padx=30)
	label7 = Label(newWindow, text ='square matrix')
	label7.grid(row = 1, column = 2)
	e6 = Entry(newWindow)
	e6.grid(row = 1, column = 3)
	label4 = Label(newWindow, text ='key')
	label4.grid(row = 2, column = 2)
	e7 = Entry(newWindow)
	e7.grid(row = 2, column = 3)
	ent = Button(newWindow, text = "decrypt", command = playfair_decrypt)
	ent.grid(row = 3, column = 3)
	e8 = Entry(newWindow)
	e8.grid(row = 4, column = 3)

wd = Tk()
wd.title("KRIPTOGRAFI")

shift_cipher = Button(wd, text=kript[0], command=shift, height=0, width=20)
shift_cipher.grid(row=0, column=0)

substitution_cipher = Button(wd, text=kript[1], command=substitution, height=0, width=20)
substitution_cipher.grid(row=0, column=1)

affine_cipher = Button(wd, text=kript[2], command=affine, height=0, width=20)
affine_cipher.grid(row=0, column=2)

vigenere_cipher = Button(wd, text=kript[3], command=vigenere, height=0, width=20)
vigenere_cipher.grid(row=1, column=0)

ev_cipher = Button(wd, text=kript[4], command=ev, height=0, width=20)
ev_cipher.grid(row=1, column=1)

hill_cipher = Button(wd, text=kript[5], command=hill, height=0, width=20)
hill_cipher.grid(row=1, column=2)

playfair_cipher = Button(wd, text=kript[6], command=playfair, height=0, width=20)
playfair_cipher.grid(row=2, column=1)

wd.mainloop()

