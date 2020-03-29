## source do makefile -> https://www.youtube.com/watch?v=Wt6KGlMkLz0

TARGET = projeto

SRC= src
INC= include
LIB= libs
BIN=bin

SOURCE = $(wildcard $(SRC)/*.c)
OBJECT = $(patsubst %,$(BIN)/%, $(notdir $(SOURCE:.c=.o)))

CC= gcc
CFLAGS= -Wall -g -I$(INC)


## Link
$(BIN)/$(TARGET): $(OBJECT)
	$(CC) -o $@ $^

## Compile
$(BIN)/%.o: $(SRC)/%.c
	$(CC) $(CFLAGS) -c $< -o $@


clean:
	rm $(OBJECT) $(BIN)/$(TARGET)


help:
	@echo "src: $(SOURCE)"
	@echo "obj: $(OBJECT)"


