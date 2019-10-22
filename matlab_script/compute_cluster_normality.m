clear;
clc;
runtime=50;
name={'Disney';'4area';'arxivSmall';'imdb';'Enron'};
for dataset=5:5
    disp(['run ' name{dataset}])
    filename=['/Users/Desktop/realdata/graphdata/' name{dataset} '/' name{dataset} '.mat'];
    load(filename);
    
    filename=['/Users/Desktop/realdata/graphdata/' name{dataset} '/Data.txt'];
    data=importdata(filename);
    
    filename=['/Users/Desktop/realdata/graphdata/' name{dataset} '/Uni/DM.txt'];
    DM=importdata(filename);
    
    focusData = data*DM;
    
    filename=['/Users/Desktop/realdata/graphdata/' name{dataset} '/candidateSeed.dat'];
    candidateSeed= importdata(filename);
    n=size(A,1);
    for run=1:runtime
%         disp(['run ' num2str(run)])
%         cluDir=['/Users/shuaiye/Desktop/realdata/graphdata/' name{dataset} '/' num2str(run) '_loclu_cluster.txt'];

cluDir=['/Users/shuaiye/Desktop/realdata/graphdata/' name{dataset} '/amen_cluster_' num2str(run) '.txt'];
%                 cluDir=['/Users/Desktop/realdata/graphdata/' name{dataset} '/hkgrow_weighted_' num2str(run) '.txt'];
%                 cluDir=['/Users/Desktop/realdata/graphdata/' name{dataset} '/hkgrow_' num2str(run) '.txt'];
%                 cluDir=['/Users/Desktop/realdata/graphdata/' name{dataset} '/focused_cluster_' num2str(run) '.txt'];
        clu=importdata(cluDir);
        seed=candidateSeed(run);
        
        f=clu;
        As = A(f,f);
        Fs = focusData(f,:);
        mulfactor = sum(sum(A)) / sum(sum(As));        
        scores = compute_normality( A, data, DM', clu);
        result(run,1)=full(scores);
    end
    mean(result)
    %     std(result)
end