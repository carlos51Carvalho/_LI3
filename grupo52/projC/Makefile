TARGET = projeto


SRC= src
INC= include
LIB= libs
BIN=bin


SOURCE = $(wildcard $(SRC)/*.c)
OBJECT = $(patsubst %,$(BIN)/%, $(notdir $(SOURCE:.c=.o)))


CC= gcc
CFLAGS= -Wall -g -I$(INC)


$(BIN)/$(TARGET): $(OBJECT)
	$(CC) -o $@ $^

$(BIN)/%.o: $(SRC)/%.c
	$(CC) $(CFLAGS) -c $< -o $@

clean:
	rm $(BIN)/*


help:
	@echo "src: $(SOURCE)"
	@echo "obj: $(OBJECT)"

