## baseado no video de explicação de makefile -> https://www.youtube.com/watch?v=Wt6KGlMkLz0

TARGET = projeto

SRC= src
INC= include
LIB= libs
BIN= bin
DOC= docs

SOURCE = $(wildcard $(SRC)/*.c)
OBJECT = $(patsubst %,$(BIN)/%, $(notdir $(SOURCE:.c=.o)))

CC= gcc
CFLAGS= -Wall -g -I$(INC) -ansi -D_GNU_SOURCE 


## Link
$(BIN)/$(TARGET): $(OBJECT)
	$(CC) -o $@ $^

## Compile
$(BIN)/%.o: $(SRC)/%.c
	$(CC) $(CFLAGS) -c $< -o $@

program: $(BIN)/$(TARGET)



clean:
	rm $(OBJECT) $(BIN)/$(TARGET)


help:
	@echo "src: $(SOURCE)"
	@echo "obj: $(OBJECT)"


