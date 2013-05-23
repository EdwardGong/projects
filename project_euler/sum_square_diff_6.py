def sum_of_numbers(n):
    return (n * (n + 1))/2

def sum_of_squares(n):
    sum = 0
    for i in range(1, n + 1):
        sum = sum + (i ** 2)
    return sum

n = 100
print sum_of_numbers(n) ** 2 - sum_of_squares(n)
