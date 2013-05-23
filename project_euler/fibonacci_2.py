term_1 = 0
term_2 = 1
fib_sum = 0
while (True):
    sum = term_1 + term_2
    if (sum > 4000000):
        break
    term_1 = term_2
    term_2 = sum
    if (sum % 2 == 0):
        fib_sum = fib_sum + sum

print fib_sum
