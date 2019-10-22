function scores = compute_normality( A, X_Total, weights, community, varargin )
%AMEN_RANK Rank egonets by their amen circle normality score
%   Detailed explanation goes here

% parameters
parser = inputParser;
addOptional(parser,'min_degree', 1);
addOptional(parser,'max_degree', Inf);
addOptional(parser,'norm', 'L1');
addOptional(parser,'node_filter', []);

varargin{:};
parse(parser, varargin{:});

min_degree = parser.Results.min_degree;
max_degree = parser.Results.max_degree;
p_norm = parser.Results.norm;
node_filter = parser.Results.node_filter;

degrees = sum(A,2);
M = nnz(A)/2;

X_Total_Transpose = X_Total.';

% X = [];
X=X_Total;

[~, scores] = amen_learn_weights( A, X, community, degrees,  M, p_norm, @amen_objective, X_Total_Transpose, weights );

end

