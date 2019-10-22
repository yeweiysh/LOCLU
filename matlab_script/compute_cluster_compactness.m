clear;
clc;
runtime=50;
name={'Disney';'4area';'arxivSmall';'imdb';'Enron'};
for dataset=4:4
    disp(['run ' name{dataset}])
    filename=['/Users/Desktop/realdata/graphdata/' name{dataset} '/' name{dataset} '.mat'];
    load(filename);
    
    filename=['/Users/Desktop/realdata/graphdata/' name{dataset} '/Data.txt'];
    data=importdata(filename);
    
    filename=['/Users/Desktop/realdata/graphdata/' name{dataset} '/DM.txt'];
    DM=importdata(filename);
    
    focusData = data*DM;
    
    filename=['/Users/Desktop/realdata/graphdata/' name{dataset} '/candidateSeed.dat'];
    candidateSeed= importdata(filename);
    n=size(A,1);
    for run=1:runtime
        disp(['run ' num2str(run)])
        vDir=['/Users/Desktop/realdata/graphdata/' name{dataset} '/LOCLU/v.dat'];
        peigs=importdata(vDir);
        
%         cluDir=['/Users/shuaiye/Desktop/realdata/graphdata/' name{dataset} '/LOCLU/' num2str(run) '_loclu_cluster.txt'];
        cluDir=['/Users/shuaiye/Desktop/realdata/graphdata/' name{dataset} '/amen_cluster_' num2str(run) '.txt'];
%                 cluDir=['/Users/shuaiye/Desktop/realdata/graphdata/' name{dataset} '/hkgrow_weighted_' num2str(run) '.txt'];
%                 cluDir=['/Users/shuaiye/Desktop/realdata/graphdata/' name{dataset} '/hkgrow_' num2str(run) '.txt'];
%                 cluDir=['/Users/shuaiye/Desktop/realdata/graphdata/' name{dataset} '/focused_cluster_' num2str(run) '.txt'];
        clu=importdata(cluDir);
        seed=candidateSeed(run);
        
        tmpdata=focusData(clu,:);
        [sorted,~]=sort(tmpdata);
        [dip,~,~,p_value,~,~,~,~,~,~] = HartigansDipSignifTest (sorted, 1000);
        au=dip;
        
        gs=peigs(clu,:);
        [sorted,~]=sort(gs);
        [dip,~,~,p_value,~,~,~,~,~,~] = HartigansDipSignifTest (sorted, 1000);
        gu=dip;
     
        result(run,1)=au;
        result(run,2)=gu;
        result(run,3)=au+gu;

    end
    mean(result)
    %     std(result)
end