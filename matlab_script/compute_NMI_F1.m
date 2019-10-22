clear;
clc;
runtime=50;
pointer=0;
for j=[1,3,5,7,9]
    j
    pointer=pointer+1;
    filename=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/label.txt'];
    label=importdata(filename);
    
    filename=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/candidateSeed.dat'];
    candidateSeed= importdata(filename);
    for run=1:runtime
        disp(['run ' num2str(run)])
        seed=candidateSeed(run);
        seedl=label(seed);
        verts=find(label==seedl);
        truelabel=ones(length(label),1);
        truelabel(verts)=2;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/' num2str(run) '_loclu_cluster.txt'];
        loclu_cluster=importdata(path);
        fmeas=compute_f1(verts,loclu_cluster);
        f1_loclu(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(loclu_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_loclu(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/origin_weighted_hkgrow_cluster_' num2str(run) '.txt'];
        origin_weighted_hkgrow_cluster=importdata(path);
        fmeas=compute_f1(verts,origin_weighted_hkgrow_cluster);
        f1_origin_weighted_hkgrow(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(origin_weighted_hkgrow_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_origin_weighted_hkgrow(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/origin_weighted_pprgrow_cluster_' num2str(run) '.txt'];
        origin_weighted_pprgrow_cluster=importdata(path);
        fmeas=compute_f1(verts,origin_weighted_pprgrow_cluster);
        f1_origin_weighted_pprgrow(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(origin_weighted_pprgrow_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_origin_weighted_pprgrow(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/weighted_hkgrow_cluster_' num2str(run) '.txt'];
        weighted_hkgrow_cluster=importdata(path);
        fmeas=compute_f1(verts,weighted_hkgrow_cluster);
        f1_weighted_hkgrow(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(weighted_hkgrow_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_weighted_hkgrow(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/weighted_pprgrow_cluster_' num2str(run) '.txt'];
        weighted_pprgrow_cluster=importdata(path);
        fmeas=compute_f1(verts,weighted_pprgrow_cluster);
        f1_weighted_pprgrow(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(weighted_pprgrow_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_weighted_pprgrow(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/hkgrow_cluster_' num2str(run) '.txt'];
        hkgrow_cluster=importdata(path);
        fmeas=compute_f1(verts,hkgrow_cluster);
        f1_hkgrow(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(hkgrow_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_hkgrow(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/pprgrow_cluster_' num2str(run) '.txt'];
        pprgrow_cluster=importdata(path);
        fmeas=compute_f1(verts,pprgrow_cluster);
        f1_pprgrow(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(pprgrow_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_pprgrow(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/origin_weighted_pgdc_cluster_' num2str(run) '.txt'];
        origin_weighted_pgdc_cluster=importdata(path);
        fmeas=compute_f1(verts,origin_weighted_pgdc_cluster);
        f1_origin_weighted_pgdc(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(origin_weighted_pgdc_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_origin_weighted_pgdc(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/weighted_pgdc_cluster_' num2str(run) '.txt'];
        weighted_pgdc_cluster=importdata(path);
        fmeas=compute_f1(verts,weighted_pgdc_cluster);
        f1_weighted_pgdc(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(weighted_pgdc_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_weighted_pgdc(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/pgdc_cluster_' num2str(run) '.txt'];
        pgdc_cluster=importdata(path);
        fmeas=compute_f1(verts,pgdc_cluster);
        f1_pgdc(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(pgdc_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_pgdc(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/focused_cluster_' num2str(run) '.txt'];
        focused_cluster=importdata(path);
        fmeas=compute_f1(verts,focused_cluster);
        f1_focus(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(focused_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_focus(run,1)=NMI;
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/origin_focused_cluster_' num2str(run) '.txt'];
        origin_focused_cluster=importdata(path);
        fmeas=compute_f1(verts,origin_focused_cluster);
        f1_focus_origin(run,1)=fmeas;
        cluster=ones(length(label),1);
        cluster(origin_focused_cluster)=2;
        [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
        nmi_focus_origin(run,1)=NMI;
        clear cluster;
        
        
        k=8;
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/SG_pursuit_cluster.txt'];
        sg_cluster_origin= importdata(path);
        for i=1:k
            d=find(~isnan(sg_cluster_origin(i,:)));
            sg_cluster=sg_cluster_origin(i,d)';
            fmea=compute_f1(verts,sg_cluster);
            if isnan(fmea)
                fmea=0;
            end
            fmeasure(1,i)=fmea;
            cluster=ones(length(label),1);
            cluster(sg_cluster)=2;
            [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
            if isnan(NMI)
                NMI=0;
            end
            nmi_sg_cluster(1,i)=NMI;
        end
        f1_sgpursuit(run,1)=max(fmeasure);
        nmi_sgpursuit(run,1)=max(nmi_sg_cluster);
        clear cluster;
        
        path=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/' num2str(j) '/uncut_cluster.txt'];
        uncut_cluster_origin= importdata(path);
        for i=1:k
            uncut_cluster=find(uncut_cluster_origin==i);
            fmea=compute_f1(verts,uncut_cluster);
            if isnan(fmea)
                fmea=0;
            end
            fmeasure(1,i)=fmea;
            cluster=ones(length(label),1);
            cluster(uncut_cluster)=2;
            [NMI,~,~,~]=ANMI_analytical_11(truelabel,cluster);
            if isnan(NMI)
                NMI=0;
            end
            nmi_uncut_cluster(1,i)=NMI;
        end
        f1_uncut(run,1)=max(fmeasure);
        nmi_uncut(run,1)=max(nmi_uncut_cluster);
        clear cluster;
        
    end
    %     disp(['loclu_f1: '])
    [row, ~] = find(~isnan(f1_loclu));
    f1_all(1,pointer)=mean(f1_loclu(row));
    %     disp(['loclu_NMI: '])
    [row, ~] = find(~isnan(nmi_loclu));
    nmi_all(1,pointer)=mean(nmi_loclu(row));
    
    %     disp(['origin_weighted_hkgrow_f1: '])
    [row, ~] = find(~isnan(f1_origin_weighted_hkgrow));
    f1_all(2,pointer)=mean(f1_origin_weighted_hkgrow(row));
    %     disp(['origin_weighted_hkgrow_NMI: '])
    [row, ~] = find(~isnan(nmi_origin_weighted_hkgrow));
    nmi_all(2,pointer)=mean(nmi_origin_weighted_hkgrow(row));
    
    %     disp(['origin_weighted_pprgrow_f1: '])
    [row, ~] = find(~isnan(f1_origin_weighted_pprgrow));
    f1_all(3,pointer)=mean(f1_origin_weighted_pprgrow(row));
    %     disp(['origin_weighted_pprgrow_NMI: '])
    [row, ~] = find(~isnan(nmi_origin_weighted_pprgrow));
    nmi_all(3,pointer)=mean(nmi_origin_weighted_pprgrow(row));
    
    %     disp(['weighted_hkgrow_f1: '])
    [row, ~] = find(~isnan(f1_weighted_hkgrow));
    f1_all(4,pointer)=mean(f1_weighted_hkgrow(row));
    %     disp(['weighted_hkgrow_NMI: '])
    [row, ~] = find(~isnan(nmi_weighted_hkgrow));
    nmi_all(4,pointer)=mean(nmi_weighted_hkgrow(row));
    
    %     disp(['weighted_pprgrow_f1: '])
    [row, ~] = find(~isnan(f1_weighted_pprgrow));
    f1_all(5,pointer)=mean(f1_weighted_pprgrow(row));
    %     disp(['weighted_pprgrow_NMI: '])
    [row, ~] = find(~isnan(nmi_weighted_pprgrow));
    nmi_all(5,pointer)=mean(nmi_weighted_pprgrow(row));
    
    %     disp(['hkgrow_f1: '])
    [row, ~] = find(~isnan(f1_hkgrow));
    f1_all(6,pointer)=mean(f1_hkgrow(row));
    %     disp(['hkgrow_NMI: '])
    [row, ~] = find(~isnan(nmi_hkgrow));
    nmi_all(6,pointer)=mean(nmi_hkgrow(row));
    
    %     disp(['pprgrow_f1: '])
    [row, ~] = find(~isnan(f1_pprgrow));
    f1_all(7,pointer)=mean(f1_pprgrow(row));
    %     disp(['pprgrow_NMI: '])
    [row, ~] = find(~isnan(nmi_pprgrow));
    nmi_all(7,pointer)=mean(nmi_pprgrow(row));
    
    %     disp(['origin_weighted_pgdc_f1: '])
    [row, ~] = find(~isnan(f1_origin_weighted_pgdc));
    f1_all(8,pointer)=mean(f1_origin_weighted_pgdc(row));
    %     disp(['origin_weighted_pgdc_NMI: '])
    [row, ~] = find(~isnan(nmi_origin_weighted_pgdc));
    nmi_all(8,pointer)=mean(nmi_origin_weighted_pgdc(row));
    
    %     disp(['weighted_pgdc_f1: '])
    [row, ~] = find(~isnan(f1_weighted_pgdc));
    f1_all(9,pointer)=mean(f1_weighted_pgdc(row));
    %     disp(['weighted_pgdc_NMI: '])
    [row, ~] = find(~isnan(nmi_weighted_pgdc));
    nmi_all(9,pointer)=mean(nmi_weighted_pgdc(row));
    
    %     disp(['pgdc_f1: '])
    [row, ~] = find(~isnan(f1_pgdc));
    f1_all(10,pointer)=mean(f1_pgdc(row));
    %     disp(['pgdc_NMI: '])
    [row, ~] = find(~isnan(nmi_pgdc));
    nmi_all(10,pointer)=mean(nmi_pgdc(row));
    
    [row, ~] = find(~isnan(f1_sgpursuit));
    f1_all(11,pointer)=mean(f1_sgpursuit(row));
    [row, ~] = find(~isnan(nmi_sgpursuit));
    nmi_all(11,pointer)=mean(nmi_sgpursuit(row));
    
    [row, ~] = find(~isnan(f1_uncut));
    f1_all(12,pointer)=mean(f1_uncut(row));
    [row, ~] = find(~isnan(nmi_uncut));
    nmi_all(12,pointer)=mean(nmi_uncut(row));
    
    [row, ~] = find(~isnan(f1_focus));
    f1_all(13,pointer)=mean(f1_focus(row));
    [row, ~] = find(~isnan(nmi_focus));
    nmi_all(13,pointer)=mean(nmi_focus(row));
    
    [row, ~] = find(~isnan(f1_focus_origin));
    f1_all(14,pointer)=mean(f1_focus_origin(row));
    [row, ~] = find(~isnan(nmi_focus_origin));
    nmi_all(14,pointer)=mean(nmi_focus_origin(row));
    
end

algorithm=["loclu:","origin_weighted_hkgrow:","origin_weighted_pprgrow:","weighted_hkgrow:",...
    "weighted_pprgrow:","hkgrow:","pprgrow:","origin_weighted_pgdc:",...
    "weighted_pgdc:","pgdc:","sg-pursuit:","uncut:","focusco:","focusco_origin:"];
result_out=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/F1.txt'];
fileID = fopen(result_out,'w');
for i=1:14
    fprintf(fileID, '%s', algorithm(i));
    for j=1:size(f1_all,2)-1
        fprintf(fileID, '%f,', f1_all(i,j));
    end
    fprintf(fileID, '%f\n', f1_all(i,j+1));
end
fclose(fileID);

result_out=['/Users/Desktop/graphdata/synthetic/1000nodes_20attributes_varying_ratio/NMI.txt'];
fileID = fopen(result_out,'w');
for i=1:14
    fprintf(fileID, '%s', algorithm(i));
    for j=1:size(nmi_all,2)-1
        fprintf(fileID, '%f,', nmi_all(i,j));
    end
    fprintf(fileID, '%f\n', nmi_all(i,j+1));
end
fclose(fileID);

function fmeas=compute_f1(verts,cluster)

recalls = numel(intersect(verts,cluster))/numel(verts);
precisions = numel(intersect(verts,cluster))/numel(cluster);
fmeas = 2*recalls*precisions/(recalls+precisions);

end
