# Simple AES Encryption Tool

A basic Python script for encrypting and decrypting messages using AES in CBC mode.  
**Note:** This tool is for learning and experimentation purposes. It is not cryptographically secure for real-world sensitive data.

---

## Features

- Encrypt text messages using AES-CBC.
- Decrypt AES-encrypted messages.
- Learn how AES encryption works in Python.

---

## Requirements

- Python 3.6+
- `pycryptodome` library

Install dependencies with pip:

```bash
pip install pycryptodome
```
---

# Usage

```bash
from aes_tool import encrypt, decrypt

# Encrypt a message
ciphertext, key, iv = encrypt("Hello, world!")

# Decrypt a message
plaintext = decrypt(ciphertext, key, iv)
print(plaintext)  # Output: Hello, world!
```

---

# Warning

This implementation is not secure for real encryption needs:

Uses a fixed block size.
Lacks secure key storage.
Intended for educational purposes only.

---

# Contributing

Feel free to submit issues or pull requests to improve this tool.

---

# License

This project is licensed under the MIT License.
