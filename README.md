# Adversarial Search Camelot

## Introduction
Adversarial search involves multiple agents in a competitive environment and is widespread strategy in multi-player games. This project implements two AI agents which run based on variants of an adversarial search algorithm, NEGAMAX (which is yet another variant of MINIMAX) on a classic board game called Camelot. The performance of the two agents are compared and analysed through a metric on the evaluation functions and difference in the number of possible evaluations for each agent.

## Camelot
Camelot is a classic strategy board game for two players. The original game is played on a board of 160 squares, which is roughly rectangular (12×14), with three squares removed from each of the four corners, and four extra squares extending outside the main rectangle, two each at the top and bottom of the board. These two-square areas are called the castles. Each player starts the game with fourteen pieces: four knights and ten men. Multiple leaps over a player’s own piece is permitted. Leap over an enemy’s piece to a vacant space immediately beyond captures the piece (removes the piece from the board). Game
ends when both the castles are occupied by the opponent. This game has adequate playing time (15 minutes), and minimal rules unlike chess.

This project implements a minified camelot version where the number of squares is brought down to 88 and 6 pieces on either side. The rules of the game remain the same as the original. Some of the design choices we made to simplify the implementation are as follows:- To simplify the implementation all pieces are assumed to be of the same type (PAWN). The objective of the game is to be the first player to occupy the opponent's castle with one of your own pieces, or, to capture all of your opponent's pieces while retaining two or more of your own pieces.

## Algorithm
NegaMax operates on the same game trees as those used with the minimax search algorithm. Each node and root node in the tree are game states (such as game board configuration) of a two player game. Transitions to child nodes represent moves available to a player who's about to play from a given node. The negamax search objective is to find the node score value for the player who is playing at the root node.

Alpha–beta pruning is a search algorithm that seeks to decrease the number of nodes that are evaluated by the minimax algorithm in its search tree. It stops completely evaluating a move when at least one possibility has been found that proves the move to be worse than a previously examined move. Such moves need not be evaluated further. When applied to a standard minimax tree, it returns the same move as minimax would, but prunes away branches that cannot possibly
influence the final decision.

Algorithm optimizations for minimax are also equally applicable for Negamax. Alpha-beta pruning can decrease the number of nodes the negamax algorithm evaluates in a search tree in a manner similar with its use with the minimax algorithm.

## Conclusion
Different evaluation functions were used to create different gaming strategies for the algorithm. The algorithms used were MINIMAX and NEGAMAX algorithms. NEGAMAX is a variation of MINIMAX which uses same evaluation function for both agents as opposed to Minimax that uses two. Alpha-beta pruning was employed to reduce the number of nodes evaluated by the tree searches. NEGAMAX with Alpha-beta pruning proved to be very efficient in terms of number of moves explored in the search tree.