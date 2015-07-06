from random import randint
# Problem 84

def update_pos(pos, board, freq_cc, freq_ch):
    pos_mod = pos % 40
    if pos_mod == 30: # go to jail
        freqs['JAIL'] += 1
        pos = 10
    elif pos_mod in (2, 17, 33): # community chest
        cc_card = cc[freq_cc % 16]
        freq_cc += 1
        if cc_card == 'Advance to GO':
            freqs['GO'] += 1
            pos = 0
        elif cc_card == 'Go to JAIL':
            freqs['JAIL'] += 1
            pos = 10
        else:
            freqs[board[pos_mod]] += 1
    elif pos_mod in (7, 22, 36): # chance
        ch_card = ch[freq_ch % 16]
        freq_ch += 1
        if ch_card == 'Advance to GO':
            freqs['GO'] += 1
            pos = 0
        elif ch_card == 'Go to JAIL':
            freqs['JAIL'] += 1
            pos = 10
        elif ch_card == 'Go to C1':
            freqs['C1'] += 1
            pos = 11
        elif ch_card == 'Go to E3':
            freqs['E3'] += 1
            pos = 24
        elif ch_card == 'Go to H2':
            freqs['H2'] += 1
            pos = 39
        elif ch_card == 'Go to R1':
            freqs['R1'] += 1
            pos = 5
        elif ch_card == 'Go to next R':
            if pos_mod == 7:
                freqs['R2'] += 1
                pos = 15
            elif pos_mod == 22:
                freqs['R3'] += 1
                pos = 25
            else:
                freqs['R1'] += 1
                pos = 5
        elif ch_card == 'Go to next U':
            if pos_mod == 7 or pos_mod == 36:
                freqs['U1'] += 1
                pos = 12
            else:
                freqs['U2'] += 1
                pos = 28
        elif ch_card == 'Go back 3 squares':
            pos -= 3
            return update_pos(pos, board, freq_cc, freq_ch)  
        else:
            freqs[board[pos_mod]] += 1 
    else:
        freqs[board[pos_mod]] += 1
    return (pos, freq_cc, freq_ch)
        
freqs = dict.fromkeys(['GO', 'A1', 'CC1', 'A2', 'T1', 'R1', 'B1', 'CH1', 'B2', 'B3',
                      'JAIL', 'C1', 'U1', 'C2', 'C3', 'R2', 'D1', 'CC2', 'D2', 'D3',
                      'FP', 'E1', 'CH2', 'E2', 'E3', 'R3', 'F1', 'F2', 'U2', 'F3',
                      'G2J', 'G1', 'G2', 'CC3', 'G3', 'R4', 'CH3', 'H1', 'T2', 'H2'], 0)
                      
board = ('GO', 'A1', 'CC1', 'A2', 'T1', 'R1', 'B1', 'CH1', 'B2', 'B3',
        'JAIL', 'C1', 'U1', 'C2', 'C3', 'R2', 'D1', 'CC2', 'D2', 'D3',
        'FP', 'E1', 'CH2', 'E2', 'E3', 'R3', 'F1', 'F2', 'U2', 'F3',
        'G2J', 'G1', 'G2', 'CC3', 'G3', 'R4', 'CH3', 'H1', 'T2', 'H2')
        
cc = ('', '', 'Advance to GO', '', '', '', '', '', '', 'Go to JAIL', '', '', '', '', '', '')
ch = ('Advance to GO', '', 'Go to JAIL', 'Go to C1', 'Go to E3', 'Go to H2', '', '', 'Go to R1', 
      'Go to next R', '', 'Go to next R', 'Go to next U', '', '', 'Go back 3 squares')
                 
nb_rolls = 1000000
dice_nb_sides = 4
pos = 0
consec_doubles = 0
freq_cc = 0
freq_ch = 0

for i in range(nb_rolls):
    dice_a = randint(1, dice_nb_sides)
    dice_b = randint(1, dice_nb_sides)
    if dice_a == dice_b:
        consec_doubles += 1
        if consec_doubles == 3:
            freqs['JAIL'] += 1
            pos = 10 
            consec_doubles = 0
        else:
            pos += dice_a + dice_b
            pos, freq_cc, freq_ch = update_pos(pos, board, freq_cc, freq_ch)
    else:
        pos += dice_a + dice_b
        pos, freq_cc, freq_ch = update_pos(pos, board, freq_cc, freq_ch)
        consec_doubles = 0
        
freqs.update((x, y / nb_rolls) for x, y in freqs.items())
top_three_probas = sorted(list(freqs.values()))[:-4:-1]
top_three_squares = list()
for x in top_three_probas:
    top_three_squares.append(list(key for key in freqs.keys() if freqs[key] == x)[0])
print('The solution is %0.2d' % board.index(top_three_squares[0]) + '%0.2d' % board.index(top_three_squares[1]) + '%0.2d' % board.index(top_three_squares[2]))



